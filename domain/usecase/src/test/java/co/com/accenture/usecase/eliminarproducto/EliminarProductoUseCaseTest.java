package co.com.accenture.usecase.eliminarproducto;

import co.com.accenture.model.producto.gateways.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EliminarProductoUseCaseTest {

    @InjectMocks
    private EliminarProductoUseCase eliminarProductoUseCase;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void noDeberiaEliminarProducto() {
        // Arrange
        String id = "1";

        given(productoRepository.existeById(id))
                .willReturn(Mono.just(false));

        // Act
        var result = eliminarProductoUseCase.action(id);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Producto no encontrado con ID: " + id))
                .verify();
    }

    @Test
    void deberiaEliminarProducto() {
        // Arrange
        String id = "1";

        given(productoRepository.existeById(id))
                .willReturn(Mono.just(true));

        given(productoRepository.eliminarProducto(id))
                .willReturn(Mono.empty());

        // Act
        var result = eliminarProductoUseCase.action(id);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

}