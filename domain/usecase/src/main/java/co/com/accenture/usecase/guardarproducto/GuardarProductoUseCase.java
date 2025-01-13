package co.com.accenture.usecase.guardarproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarProductoUseCase {

    private final ProductoRepository productoRepository;

    private final SucursalRepository sucursalRepository;

    public Mono<Producto> action(Producto producto) {
        return sucursalRepository.findById(producto.getSucursalId())
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontro la sucursal")))
                .flatMap(sucursal -> {
                    producto.setFranquiciaId(sucursal.getFranquiciaId());
                    return productoRepository.guardarProducto(producto);
                })
                .map(producto1 -> {
                    producto1.setNombre(producto.getNombre());
                    return producto1;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("No se pudo guardar el producto")));
    }
}
