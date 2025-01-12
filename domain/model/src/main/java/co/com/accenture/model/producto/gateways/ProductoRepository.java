package co.com.accenture.model.producto.gateways;

import co.com.accenture.model.producto.Producto;
import reactor.core.publisher.Mono;

public interface ProductoRepository {
    Mono<Producto> guardarProducto(Producto producto);

    Mono<Void> eliminarProducto(String id);
    Mono<Boolean> existeById(String id);
}
