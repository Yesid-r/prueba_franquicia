package co.com.accenture.mongo.repository.Producto;

import co.com.accenture.mongo.entity.ProductoEntity;
import co.com.accenture.mongo.entity.SucursalEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;

public interface MongoDBRepositoryProducto extends ReactiveMongoRepository<ProductoEntity, String>, ReactiveQueryByExampleExecutor<ProductoEntity> {

    Flux<Object> findAllByFranquiciaId(String franquiciaId);
}
