package co.com.accenture.usecase.eliminarproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EliminarProductoUseCase {
    private final ProductoRepository productoRepository;


    public Mono<Void> action(String id) {
        return productoRepository.existeById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return productoRepository.eliminarProducto(id);
                    }
                    return Mono.error(new RuntimeException("Producto no encontrado con ID: " + id));
                });
    }

}
