package co.com.accenture.usecase.actualizarnombreproducto;

import co.com.accenture.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarNombreProductoUseCase {
    private final ProductoRepository productoRepository;

    public Mono<Void> action(String id, String nombre) {
        return productoRepository.existeById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return productoRepository.actualizarNombreProducto(id, nombre);
                    }
                    return Mono.error(new RuntimeException("Producto no encontrado con ID: " + id));
                });
    }
}
