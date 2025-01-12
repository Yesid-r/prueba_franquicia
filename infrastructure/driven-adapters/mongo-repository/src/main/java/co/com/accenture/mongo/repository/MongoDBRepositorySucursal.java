package co.com.accenture.mongo.repository;

import co.com.accenture.mongo.entity.FranquiciaEntity;
import co.com.accenture.mongo.entity.SucursalEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepositorySucursal extends ReactiveMongoRepository<SucursalEntity, String>, ReactiveQueryByExampleExecutor<SucursalEntity> {
}
