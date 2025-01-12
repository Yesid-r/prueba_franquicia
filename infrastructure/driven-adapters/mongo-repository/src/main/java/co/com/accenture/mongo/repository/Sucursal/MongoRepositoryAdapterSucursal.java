package co.com.accenture.mongo.repository.Sucursal;

import co.com.accenture.model.sucursal.Sucursal;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import co.com.accenture.mongo.entity.SucursalEntity;
import co.com.accenture.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapterSucursal extends AdapterOperations<Sucursal, SucursalEntity, String, MongoDBRepositorySucursal>
implements SucursalRepository
{

    public MongoRepositoryAdapterSucursal(MongoDBRepositorySucursal repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Sucursal.class));
    }


    @Override
    public Mono<Sucursal> guardarSucursal(Sucursal sucursal) {
        return this.save(sucursal);
    }
}
