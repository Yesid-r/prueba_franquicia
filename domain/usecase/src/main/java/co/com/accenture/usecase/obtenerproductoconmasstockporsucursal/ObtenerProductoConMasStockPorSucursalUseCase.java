package co.com.accenture.usecase.obtenerproductoconmasstockporsucursal;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ObtenerProductoConMasStockPorSucursalUseCase {

    private final ProductoRepository productoRepository;

    public Flux<Producto> action(String franquiciaId) {
        return productoRepository.findByFranquiciaId(franquiciaId)
                .groupBy(Producto::getSucursalId)
                .flatMap(group ->
                        group
                                .sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock())) // Aseguramos el orden descendente
                                .next()
                );
    }
}
