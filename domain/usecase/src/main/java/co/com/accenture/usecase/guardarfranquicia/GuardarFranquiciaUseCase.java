package co.com.accenture.usecase.guardarfranquicia;

import co.com.accenture.model.franquicia.Franquicia;
import co.com.accenture.model.franquicia.gateways.FranquiciaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarFranquiciaUseCase {

    private final FranquiciaRepository franquiciaRepository;

    public Mono<Franquicia> action(Franquicia franquicia){
        return franquiciaRepository.guardarFranquicia(franquicia);
    }
}
