package com.teddy.lc4e.plugins.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * Created by teddy on 2015/6/25.
 */
public class BaseMongoRepositoryFactoryBean<R extends MongoRepository<T, I>, T, I extends Serializable>
        extends MongoRepositoryFactoryBean<R, T, I> {

        @Override
        protected RepositoryFactorySupport getFactoryInstance(
                MongoOperations operations) {
                return new BaseMongoRepositoryFactory<T,I>( operations );
        }

        private static class BaseMongoRepositoryFactory<T, ID extends Serializable>
                extends MongoRepositoryFactory {

                private MongoOperations mongo;
                public BaseMongoRepositoryFactory(MongoOperations mongoOperations) {
                        super(mongoOperations);
                        this.mongo = mongoOperations;
                }

                @SuppressWarnings("unchecked")
                protected Object getTargetRepository(RepositoryMetadata metadata) {

                        MongoPersistentEntity<T> persistentEntity = (MongoPersistentEntity<T>) mongo.getConverter().getMappingContext().getPersistentEntity(metadata.getDomainType());

                        MongoEntityInformation<T, ID> mongometa = new MappingMongoEntityInformation<T, ID>(persistentEntity);

                        return new BaseMongoRepositoryImpl<>(mongometa,  mongo);
                }

                protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                        return BaseMongoRepository.class;
                }
        }
}
