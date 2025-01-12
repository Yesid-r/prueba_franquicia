package co.com.accenture.usecase.guardarsucursal;

import co.com.accenture.model.sucursal.Sucursal;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarSucursalUseCase {

    private final SucursalRepository sucursalRepository;

    public Mono<Sucursal> action(Sucursal sucursal){
        return sucursalRepository.guardarSucursal(sucursal)
                .map(sucursal1 -> {
                    sucursal1.setNombre(sucursal.getNombre());
                    return sucursal1;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("No se pudo guardar la sucursal")))
                ;
    }
}
