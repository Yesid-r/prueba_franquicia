package co.com.accenture.usecase.actualizarnombreproducto;

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
class ActualizarNombreProductoUseCaseTest {

    @InjectMocks
    private ActualizarNombreProductoUseCase actualizarNombreProductoUseCase;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void noDeberiaActualizarNombreProducto() {
        // Arrange
        String id = "1";
        String nombre = "Nuevo Nombre";

        given(productoRepository.existeById(id))
                .willReturn(Mono.just(false));

        // Act
        var result = actualizarNombreProductoUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Producto no encontrado con ID: " + id))
                .verify();
    }
    @Test
    void deberiaActualizarNombreProducto() {
        // Arrange
        String id = "1";
        String nombre = "Nuevo Nombre";

        given(productoRepository.existeById(id))
                .willReturn(Mono.just(true));

        given(productoRepository.actualizarNombreProducto(id, nombre))
                .willReturn(Mono.empty());

        // Act
        var result = actualizarNombreProductoUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

}