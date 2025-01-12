package co.com.accenture.usecase.actualizarnombrefranquicia;

import co.com.accenture.model.franquicia.gateways.FranquiciaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarNombreFranquiciaUseCase {
    private final FranquiciaRepository franquiciaRepository;

    public Mono<Void> action(String id, String nombre) {
        return franquiciaRepository.existeById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return franquiciaRepository.actualizarNombre(id, nombre);
                    }
                    return Mono.error(new RuntimeException("Franquicia no encontrada con ID: " + id));
                });
    }
}
