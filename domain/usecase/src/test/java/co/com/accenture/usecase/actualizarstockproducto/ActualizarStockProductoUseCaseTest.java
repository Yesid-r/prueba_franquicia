package co.com.accenture.usecase.actualizarstockproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActualizarStockProductoUseCaseTest {

    @InjectMocks
    private ActualizarStockProductoUseCase actualizarStockProductoUseCase;

    @Mock
    private ProductoRepository productoRepository;


    @Test
    void noDeberiaActualizarStockProductoConProductoNoEncontrado() {
        // Arrange
        String id = "456";
        Integer cantidad = 10;


        when(productoRepository.existeById(id)).thenReturn(Mono.just(false));
        when(productoRepository.findById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(actualizarStockProductoUseCase.action(id, cantidad))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Producto no encontrado con ID: " + id))
                .verify();


        verify(productoRepository).existeById(id);
        verifyNoMoreInteractions(productoRepository);
    }
    @Test
    void deberiaActualizarStockProducto() {
        // Arrange
        String id = "789";
        Integer cantidad = 20;
        Producto producto = new Producto();
        producto.setId(id);
        producto.setStock(10);

        when(productoRepository.existeById(id)).thenReturn(Mono.just(true));
        when(productoRepository.findById(id)).thenReturn(Mono.just(producto));
        when(productoRepository.guardarProducto(any(Producto.class))).thenReturn(Mono.just(producto));

        // Act & Assert
        StepVerifier.create(actualizarStockProductoUseCase.action(id, cantidad))
                .expectComplete()
                .verify();

        verify(productoRepository).existeById(id);
        verify(productoRepository).findById(id);
        verify(productoRepository).guardarProducto(argThat(p -> p.getStock()==(cantidad)));
        verifyNoMoreInteractions(productoRepository);
    }

}