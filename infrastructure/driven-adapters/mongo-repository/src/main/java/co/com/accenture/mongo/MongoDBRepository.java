package co.com.accenture.mongo;

import co.com.accenture.mongo.entity.FranquiciaEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<FranquiciaEntity, String>, ReactiveQueryByExampleExecutor<FranquiciaEntity> {
}
