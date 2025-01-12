package co.com.accenture.model.franquicia.gateways;

import co.com.accenture.model.franquicia.Franquicia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaRepository {

    Mono<Franquicia> guardarFranquicia(Franquicia franquicia);

    Mono<Franquicia> encontrarporId(String id);

    Mono<Boolean> existeById(String id);

    Mono<Void> actualizarNombre(String id, String nombre);
}
