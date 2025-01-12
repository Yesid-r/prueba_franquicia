package co.com.accenture.usecase.guardarproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarProductoUseCase {

    private final ProductoRepository productoRepository;

    public Mono<Producto> action(Producto producto){
       return productoRepository.guardarProducto(producto)
               .map(producto1 -> {
                   producto1.setNombre(producto.getNombre());
                   return producto1;
               })
               .switchIfEmpty(Mono.error(new RuntimeException("No se pudo guardar el producto")));
    }
}
