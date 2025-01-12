package co.com.accenture.mongo.repository.Producto;

import co.com.accenture.mongo.entity.ProductoEntity;
import co.com.accenture.mongo.entity.SucursalEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepositoryProducto extends ReactiveMongoRepository<ProductoEntity, String>, ReactiveQueryByExampleExecutor<ProductoEntity> {
}
