package co.com.accenture.usecase.actualizarnombresucursal;

import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarNombreSucursalUseCase {

    private final SucursalRepository sucursalRepository;

    public Mono<Void> action(String id, String nombre) {
        return sucursalRepository.existeById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return sucursalRepository.actualizarNombreSucursal(id, nombre);
                    }
                    return Mono.error(new RuntimeException("Sucursal no encontrada con ID: " + id));
                });
    }
}
