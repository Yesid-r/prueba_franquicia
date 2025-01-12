package co.com.accenture.usecase.actualizarnombrefranquicia;


import co.com.accenture.model.franquicia.gateways.FranquiciaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ActualizarNombreFranquiciaUseCaseTest {

    @InjectMocks
    private ActualizarNombreFranquiciaUseCase actualizarNombreFranquiciaUseCase;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @Test
    void noDeberiaActualizarNombre() {
        // Arrange
        String id = "1";
        String nombre = "Nueva Franquicia";

        given(franquiciaRepository.existeById(id))
                .willReturn(Mono.just(false));

        // Act
        var result = actualizarNombreFranquiciaUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Franquicia no encontrada con ID: " + id))
                .verify();
    }

    @Test
    void deberiaActualizarNombre() {
        // Arrange
        String id = "1";
        String nombre = "Nueva Franquicia";

        given(franquiciaRepository.existeById(id))
                .willReturn(Mono.just(true));

        given(franquiciaRepository.actualizarNombre(id, nombre))
                .willReturn(Mono.empty());

        // Act
        var result = actualizarNombreFranquiciaUseCase.action(id, nombre);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }



}