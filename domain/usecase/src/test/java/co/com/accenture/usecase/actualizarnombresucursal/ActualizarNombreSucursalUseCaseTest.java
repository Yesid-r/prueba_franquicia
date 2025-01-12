package co.com.accenture.usecase.actualizarnombresucursal;

import co.com.accenture.model.sucursal.gateways.SucursalRepository;
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
class ActualizarNombreSucursalUseCaseTest {

    @InjectMocks
    private ActualizarNombreSucursalUseCase actualizarNombreSucursalUseCase;

    @Mock
    private SucursalRepository sucursalRepository;

    @Test
    void deberiaActualizarNombreSucursal() {
        // Arrange
        String id = "1";
        String nombre = "Nuevo Nombre";

        given(sucursalRepository.existeById(id))
                .willReturn(Mono.just(true));

        given(sucursalRepository.actualizarNombreSucursal(id, nombre))
                .willReturn(Mono.empty());

        // Act
        var result = actualizarNombreSucursalUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void noDeberiaActualizarNombreSucursal() {
        // Arrange
        String id = "1";
        String nombre = "Nuevo Nombre";

        given(sucursalRepository.existeById(id))
                .willReturn(Mono.just(false));

        // Act
        var result = actualizarNombreSucursalUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Sucursal no encontrada con ID: " + id))
                .verify();
    }

}