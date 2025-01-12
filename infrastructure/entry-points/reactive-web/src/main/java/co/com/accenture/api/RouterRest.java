package co.com.accenture.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/v1/functional/franquicia"), handler::listenPOSTGuardarFranquiciaUseCase)
                .and(route(POST("/api/v1/functional/sucursal"), handler::listenPOSTGuardarSucursalUseCase))
                .and(route(POST("/api/v1/functional/producto"), handler::listenPOSTGuardarProductoUseCase))
                .and(route(DELETE("/api/v1/functional/producto/{idProducto}"), handler::listenDELETEEliminarProductoUseCase))
                .and(route(PUT("/api/v1/functional/producto/{idProducto}/stock"), handler::listenPUTActualizarStockProductoUseCase))
                .and(route(PUT("/api/v1/functional/producto/{idProducto}/nombre"), handler::listenPUTActualizarNombreProductoUseCase))
                .andRoute(PUT("/api/v1/functional/franquicia/{idFranquicia}/nombre"), handler::listenPUTActualizarNombreFranquiciaUseCase)
                .andRoute(PUT("/api/v1/functional/sucursal/{idSucursal}/nombre"), handler::listenPUTActualizarNombreSucursalUseCase);

    }
}
