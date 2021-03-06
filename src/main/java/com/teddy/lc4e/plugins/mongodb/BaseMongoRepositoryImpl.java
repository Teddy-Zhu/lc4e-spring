package com.teddy.lc4e.plugins.mongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.Position;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


public class BaseMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {

    private final MongoOperations mongoOperations;

    private final MongoEntityInformation<T, ID> entityInformation;

    private final Class<T> entityClass;

    private final String collectionName;

    public BaseMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);

        this.entityInformation = metadata;
        this.mongoOperations = mongoOperations;

        this.entityClass = entityInformation.getJavaType();
        this.collectionName = entityInformation.getCollectionName();

    }


    private Query getIdQuery(Object id) {
        return new Query(getIdCriteria(id));
    }

    private Criteria getIdCriteria(Object id) {
        return where(entityInformation.getIdAttribute()).is(id);
    }

    private Query getKeyExistsQuery(String key, boolean b) {
        return new Query(getKeyExistsCriteria(key, b));
    }

    private Criteria getKeyExistsCriteria(String key, boolean b) {
        return where(key).exists(b);
    }

    private Query getEqualQuery(String key, Object value) {
        return new Query(getEqualCriteria(key, value));
    }

    private Criteria getEqualCriteria(String key, Object value) {
        return where(key).is(value);
    }


    @Override
    public <S extends T> S insert(S entity) {
        Assert.notNull(entity, "Entity must not be null!");

        mongoOperations.insert(entity, collectionName);

        return entity;
    }


    @Override
    public <S extends T> Collection<S> insert(Collection<S> batchToInsert) {
        Assert.notNull(batchToInsert, "The given Collection<S> batchToInsert not be null!");


        mongoOperations.insert(batchToInsert, collectionName);


        return batchToInsert;
    }


    @Override
    public T findOne(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");

        if (value != null) {
            return mongoOperations.findOne(getEqualQuery(key, value).limit(1), entityClass, collectionName);
        } else    //findOne(key,null) 等价于 key: { $exists: false}
        {
            return mongoOperations.findOne(getKeyExistsQuery(key, false).limit(1), entityClass, collectionName);
        }
    }

    @Override
    public T findOne(Query query) {
        Assert.notNull(query, "The given query must not be null!");

        query.limit(1);                                //反正只要1个结果,所以limit一下,提高性能
        return mongoOperations.findOne(query, entityClass, collectionName);
    }


    @Override
    public List<T> findAll(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");

        if (value != null) {
            return findAll(getEqualQuery(key, value));
        } else {
            return findAll(getKeyExistsQuery(key, false));
        }
    }

    @Override
    public List<T> findAll(Query query) {

        if (query == null) {
            return Collections.emptyList();
        }

        return mongoOperations.find(query, entityClass, collectionName);
    }

    @Override
    public Page<T> findAll(Criteria criteria, Pageable pageable) {
        Long count = count(criteria);

        List<T> list = findAll(query(criteria).with(pageable));

        return new PageImpl<T>(list, pageable, count);
    }

    @Override
    public List<T> findAllNotEqual(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");

        return findAll(query(where(key).ne(value)));
    }


    @Override
    public long count(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");

        if (value != null) {
            return mongoOperations.count(getEqualQuery(key, value), entityClass);
        } else {
            return mongoOperations.count(getKeyExistsQuery(key, false), entityClass);
        }
    }

    @Override
    public long count(Criteria criteria) {
        return mongoOperations.count(query(criteria), entityClass);
    }


    @Override
    public boolean exists(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return count(entityInformation.getIdAttribute(), id) > 0;
    }

    @Override
    public boolean exists(String key, Object value) {
        return count(key, value) > 0;
    }


    @Override
    public void delete(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");

        if (value != null) {
            mongoOperations.remove(getEqualQuery(key, value), entityClass);
        } else {
            mongoOperations.remove(getKeyExistsQuery(key, false), entityClass);
        }
    }


    @Override
    public T findAndRemove(ID id) {
        Assert.notNull(id, "The given id must not be null!");

        return findAndRemove(getIdQuery(id));
    }

    @Override
    public T findAndRemove(String key, Object value) {
        Assert.hasText(key, "The given key must not be empty!");

        if (value != null) {
            return findAndRemove(getEqualQuery(key, value));
        } else {
            return findAndRemove(getKeyExistsQuery(key, false));
        }
    }

    @Override
    public T findAndRemove(Query query) {
        Assert.notNull(query, "The given query must not be null!");

        return mongoOperations.findAndRemove(query, entityClass, collectionName);
    }

    @Override
    public T findAndUpdate(ID id, Update update) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.notNull(update, "The given update must not be null!");

        return findAndUpdate(getIdQuery(id), update);
    }


    @Override
    public T findAndUpdate(String key, Object value, Update update) {
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(update, "The given update must not be null!");

        if (value != null) {
            return findAndUpdate(getEqualQuery(key, value), update);
        } else {
            return findAndUpdate(getKeyExistsQuery(key, false), update);
        }
    }

    private T findAndUpdate(Query query, Update update) {
        return mongoOperations.findAndModify(query, update, entityClass, collectionName);
    }


    @Override
    public void update(ID id, Update update) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.notNull(update, "The given update must not be null!");

        mongoOperations.updateFirst(getIdQuery(id), update, entityClass);
    }


    @Override
    public void update(String key, Object value, Update update) {
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");
        Assert.notNull(update, "The given update must not be null!");

        mongoOperations.updateFirst(getEqualQuery(key, value), update, entityClass);
    }


    @Override
    public void updateMulti(String key, Object value, Update update) {
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");
        Assert.notNull(update, "The given update must not be null!");

        mongoOperations.updateMulti(getEqualQuery(key, value), update, entityClass);
    }

    @Override
    public void updateMulti(Query query, Update update) {
        Assert.notNull(query, "The given query must not be null!");
        Assert.notNull(update, "The given update must not be null!");

        mongoOperations.updateMulti(query, update, entityClass);
    }


    @Override
    public void set(ID id, String key, Object value) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");

        if (value == null)                        //set(ID,key,null) 等价于 unset(ID,key)
            unset(id, key);
        else
            update(id, new Update().set(key, value));
    }


    @Override
    public void unset(ID id, String key) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");

        update(id, new Update().unset(key));
    }


    @Override
    public void inc(ID id, String key, Number inc) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(inc, "The given inc must not be null!");

        update(id, new Update().inc(key, inc));
    }


    @Override
    public void push(ID id, String key, Object value) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");

        update(id, new Update().push(key, value));
    }


    @Override
    public void pushAll(ID id, String key, Object[] values) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(values, "The given values must not be null!");

        update(id, new Update().pushAll(key, values));
    }


    @Override
    public void addToSet(ID id, String key, Object value) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");

        update(id, new Update().addToSet(key, value));
    }


    @Override
    public void pop(ID id, String key, Position pos) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(pos, "The given pos must not be null!");

        update(id, new Update().pop(key, pos));
    }


    @Override
    public void pull(ID id, String key, Object value) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(value, "The given value must not be null!");

        update(id, new Update().pull(key, value));
    }


    @Override
    public void pullAll(ID id, String key, Object[] values) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(key, "The given key must not be empty!");
        Assert.notNull(values, "The given values must not be null!");

        update(id, new Update().pullAll(key, values));
    }


    @Override
    public void rename(ID id, String oldName, String newName) {
        Assert.notNull(id, "The given id must not be null!");
        Assert.hasText(oldName, "The given oldName must not be empty!");
        Assert.hasText(newName, "The given newName must not be empty!");

        update(id, new Update().rename(oldName, newName));
    }


    @Override
    public void drop() {
        mongoOperations.dropCollection(collectionName);
    }

}
