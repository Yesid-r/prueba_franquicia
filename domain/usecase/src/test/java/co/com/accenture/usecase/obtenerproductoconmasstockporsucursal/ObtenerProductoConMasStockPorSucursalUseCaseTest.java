package co.com.accenture.usecase.obtenerproductoconmasstockporsucursal;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObtenerProductoConMasStockPorSucursalUseCaseTest {

    @Mock
    private ProductoRepository productoRepository; // Mock de ProductoRepository

    @InjectMocks
    private ObtenerProductoConMasStockPorSucursalUseCase useCase;

    @Test
    void listar() {
        // Arrange
        Producto producto1 = new Producto("1", "Producto1",10, "SucursalA", "Franquicia1"); // Producto con stock 10
        Producto producto2 = new Producto("2", "Producto2",30, "SucursalA", "Franquicia1"); // Producto con stock 30
        Producto producto3 = new Producto("3", "Producto3",20, "SucursalB", "Franquicia1"); // Producto con stock 20
        Producto producto4 = new Producto("4", "Producto4",50, "SucursalB", "Franquicia1"); // Producto con stock 50



        when(productoRepository.findByFranquiciaId("Franquicia1"))
                .thenReturn(Flux.just(producto1, producto2, producto3, producto4));

        // act
        Flux<Producto> resultado = useCase.action("Franquicia1");

        // Assert
        StepVerifier.create(resultado)
                .expectNextMatches(producto -> producto.getSucursalId().equals("SucursalA") && producto.getStock() == 30)
                .expectNextMatches(producto -> producto.getSucursalId().equals("SucursalB") && producto.getStock() == 50)
                .expectComplete()
                .verify();
    }

}