package co.com.accenture.api;

import co.com.accenture.model.franquicia.Franquicia;
import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.sucursal.Sucursal;
import co.com.accenture.usecase.eliminarproducto.EliminarProductoUseCase;
import co.com.accenture.usecase.guardarfranquicia.GuardarFranquiciaUseCase;
import co.com.accenture.usecase.guardarproducto.GuardarProductoUseCase;
import co.com.accenture.usecase.guardarsucursal.GuardarSucursalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final GuardarFranquiciaUseCase guardarFranquiciaUseCase;
    private final GuardarSucursalUseCase guardarSucursalUseCase;
    private final GuardarProductoUseCase guardarProductoUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;

    public Mono<ServerResponse> listenPOSTGuardarFranquiciaUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Franquicia.class)
                .flatMap(franquicia -> guardarFranquiciaUseCase.action(franquicia))
                .flatMap(franquicia -> ServerResponse.ok().bodyValue(franquicia))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }

    public Mono<ServerResponse> listenPOSTGuardarSucursalUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Sucursal.class)
                .flatMap(sucursal -> guardarSucursalUseCase.action(sucursal))
                .flatMap(sucursal -> ServerResponse.ok().bodyValue(sucursal))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }

    public Mono<ServerResponse> listenPOSTGuardarProductoUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Producto.class)
                .flatMap(producto -> guardarProductoUseCase.action(producto))
                .flatMap(producto -> ServerResponse.ok().bodyValue(producto))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }

    public Mono<ServerResponse> listenDELETEEliminarProductoUseCase(ServerRequest serverRequest) {
        String idProducto = serverRequest.pathVariable("idProducto");
        return eliminarProductoUseCase.action(idProducto)
                .flatMap(producto -> ServerResponse.noContent().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

}
