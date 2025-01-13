package co.com.accenture.usecase.obtenerproductoconmasstockporsucursal;

import co.com.accenture.model.DTO.ProductoDTO;
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


        Producto producto1 = Producto.builder().nombre("Samsung").stock(10).sucursalId("SucursalA").build();
        Producto producto2 = Producto.builder().nombre("Iphone").stock(50).sucursalId("SucursalB").build();
        Producto producto3 = Producto.builder().nombre("Xiaomi").stock(30).sucursalId("SucursalA").build();
        Producto producto4 = Producto.builder().nombre("Huawei").stock(20).sucursalId("SucursalB").build();
        when(productoRepository.findByFranquiciaId("Franquicia1"))
                .thenReturn(Flux.just(producto1, producto2, producto3, producto4));

        // Act
        Flux<ProductoDTO> resultado = useCase.action("Franquicia1");

        // Assert
        StepVerifier.create(resultado)
                .expectNextMatches(producto -> producto.getSucursalNombre().equals("SucursalA") && producto.getStock() == 30)
                .expectNextMatches(producto -> producto.getSucursalNombre().equals("SucursalB") && producto.getStock() == 50)
                .expectComplete()
                .verify();
    }

}