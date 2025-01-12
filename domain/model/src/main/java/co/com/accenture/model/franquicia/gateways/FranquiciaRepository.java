package co.com.accenture.model.franquicia.gateways;

import co.com.accenture.model.franquicia.Franquicia;
import reactor.core.publisher.Mono;

public interface FranquiciaRepository {

    Mono<Franquicia> guardarFranquicia(Franquicia franquicia);

    Mono<Franquicia> encontrarporId(String id);

    Mono<Franquicia> actualizarNombre(Franquicia franquicia);


}
