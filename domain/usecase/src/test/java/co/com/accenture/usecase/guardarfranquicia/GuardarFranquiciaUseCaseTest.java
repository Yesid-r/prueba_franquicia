package co.com.accenture.usecase.guardarfranquicia;

import co.com.accenture.model.franquicia.Franquicia;
import co.com.accenture.model.franquicia.gateways.FranquiciaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class GuardarFranquiciaUseCaseTest {

    @InjectMocks
    private GuardarFranquiciaUseCase guardarFranquiciaUseCase;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @Test
    public void guardarFranquiciaExitoso() {
        // Arrange
        Franquicia franquiciaInput = Franquicia.builder()
                .nombre("Nueva Franquicia")
                .build();

        Franquicia franquiciaOutput = Franquicia.builder()
                .id("1")
                .nombre("Franquicia Anterior")
                .build();

        given(franquiciaRepository.guardarFranquicia(any(Franquicia.class)))
                .willReturn(Mono.just(franquiciaOutput));

        // Act
        var result = guardarFranquiciaUseCase.action(franquiciaInput);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(franquicia -> {
                    Assertions.assertThat(franquicia.getNombre()).isEqualTo(franquiciaInput.getNombre());
                    return true;
                })
                .verifyComplete();
    }



}