package co.com.accenture.mongo;

import co.com.accenture.model.franquicia.Franquicia;
import co.com.accenture.model.franquicia.gateways.FranquiciaRepository;
import co.com.accenture.mongo.entity.FranquiciaEntity;
import co.com.accenture.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Franquicia, FranquiciaEntity, String, MongoDBRepository>
implements FranquiciaRepository
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Franquicia.class));
    }


    @Override
    public Mono<Franquicia> guardarFranquicia(Franquicia franquicia) {
        return this.save(franquicia);
    }

    @Override
    public Mono<Franquicia> encontrarporId(String id) {

        return this.repository.findById(id).map(this::toEntity);

    }


    @Override
    public Mono<Boolean> existeById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public Mono<Void> actualizarNombre(String id, String nombre) {
        return this.repository.findById(id)
                .map(entity -> {
                    entity.setNombre(nombre);
                    return entity;
                })
                .flatMap(this.repository::save)
                .then();
    }
}
