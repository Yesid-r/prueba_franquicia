package co.com.accenture.usecase.actualizarstockproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarStockProductoUseCase {

    private final ProductoRepository productoRepository;

    public Mono<Void> action(String id, Integer cantidad) {
        return validarCantidad(cantidad)
                .then(validarExistenciaProducto(id))
                .then(productoRepository.findById(id))
                .flatMap(producto -> actualizarStockProducto(producto, cantidad))
                .then();
    }

    private Mono<Void> validarCantidad(Integer cantidad) {
        if (cantidad == null) {
            return Mono.error(new RuntimeException("La cantidad no puede ser null"));
        }
        if (cantidad < 0) {
            return Mono.error(new RuntimeException("La cantidad no puede ser negativa"));
        }
        return Mono.empty();
    }
    private Mono<Void> validarExistenciaProducto(String id) {
        return productoRepository.existeById(id)
                .flatMap(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        return Mono.error(new RuntimeException("Producto no encontrado con ID: " + id));
                    }
                    return Mono.empty();
                });
    }

    private Mono<Producto> actualizarStockProducto(Producto producto, Integer cantidad) {
        producto.setStock(cantidad);
        return productoRepository.guardarProducto(producto);
    }
}
