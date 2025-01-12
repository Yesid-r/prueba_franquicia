package co.com.accenture.mongo.repository.Producto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import co.com.accenture.model.sucursal.Sucursal;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import co.com.accenture.mongo.entity.ProductoEntity;
import co.com.accenture.mongo.entity.SucursalEntity;
import co.com.accenture.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapterProducto extends AdapterOperations<Producto, ProductoEntity, String, MongoDBRepositoryProducto>
implements ProductoRepository
{

    public MongoRepositoryAdapterProducto(MongoDBRepositoryProducto repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Producto.class));
    }


    @Override
    public Mono<Producto> guardarProducto(Producto producto) {
        return this.save(producto);
    }

    @Override
    public Mono<Void> eliminarProducto(String id) {
        return this.repository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existeById(String id) {
        return this.repository.existsById(id);
    }

}
