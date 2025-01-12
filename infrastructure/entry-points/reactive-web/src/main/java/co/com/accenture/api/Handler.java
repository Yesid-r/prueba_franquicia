package co.com.accenture.api;

import co.com.accenture.model.franquicia.Franquicia;
import co.com.accenture.usecase.guardarfranquicia.GuardarFranquiciaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;


    private final GuardarFranquiciaUseCase guardarFranquiciaUseCase;

    public Mono<ServerResponse> listenPOSTGuardarFranquiciaUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Franquicia.class)
                .flatMap(franquicia -> guardarFranquiciaUseCase.action(franquicia))
                .flatMap(franquicia -> ServerResponse.ok().bodyValue(franquicia))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
