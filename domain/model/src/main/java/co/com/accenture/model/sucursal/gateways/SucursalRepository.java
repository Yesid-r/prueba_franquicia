package co.com.accenture.model.sucursal.gateways;

import co.com.accenture.model.sucursal.Sucursal;
import reactor.core.publisher.Mono;

public interface SucursalRepository {
    Mono<Sucursal> guardarSucursal(Sucursal sucursal);
}
