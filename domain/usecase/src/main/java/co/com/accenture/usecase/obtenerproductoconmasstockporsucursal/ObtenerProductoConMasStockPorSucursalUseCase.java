package co.com.accenture.usecase.obtenerproductoconmasstockporsucursal;

import co.com.accenture.model.DTO.ProductoDTO;
import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ObtenerProductoConMasStockPorSucursalUseCase {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public Flux<ProductoDTO> action(String franquiciaId) {
        return productoRepository.findByFranquiciaId(franquiciaId)
                .groupBy(Producto::getSucursalId)
                .flatMap(group ->
                        group
                                .sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()))
                                .next()
                )
                .flatMap(producto ->
                        sucursalRepository.findById(producto.getSucursalId())
                                .map(sucursal -> ProductoDTO.builder()

                                        .nombre(producto.getNombre())
                                        .stock(producto.getStock())
                                        .sucursalNombre(sucursal.getNombre())
                                        .build()
                                )
                )
                .doOnNext(System.out::println);
    }
}
