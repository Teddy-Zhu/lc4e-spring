package com.teddy.lc4e.plugins.mongodb;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
    <S extends T> S insert(S entity);


    /**
     * @param batchToInsert the list of Entities to insert.
     * @return the inserted Collection
     */
    <S extends T> Collection<S> insert(Collection<S> batchToInsert);


    /**
     * find one object using key & val
     *
     * @param key
     * @param value
     * @return
     */
    T findOne(String key, Object value);


    /**
     * find one object using query custom
     *
     * @param query
     * @return
     */
    T findOne(Query query);

    /**
     * search all objects using key & val
     *
     * @param key
     * @param value
     * @return
     */
    List<T> findAll(String key, Object value);


    List<T> findAll(Query query);

    Page<T> findAll(Criteria criteria, Pageable pageable);

    List<T> findAllNotEqual(String key, Object value);

    long count(String key, Object value);

    long count(Criteria criteria);

    boolean exists(String key, Object value);

    void delete(String key, Object value);

    T findAndUpdate(ID id, Update update);

    T findAndUpdate(String key, Object value, Update update);

    void update(ID id, Update update);

    void update(String key, Object value, Update update);

    void updateMulti(String key, Object value, Update update);

    void updateMulti(Query query, Update update);

    void set(ID id, String key, Object value);

    void unset(ID id, String key);

    void inc(ID id, String key, Number inc);

    void push(ID id, String key, Object value);

    void pushAll(ID id, String key, Object[] values);

    void addToSet(ID id, String key, Object value);

    void pop(ID id, String key, Position pos);

    void pull(ID id, String key, Object value);

    void pullAll(ID id, String key, Object[] values);

    void rename(ID id, String oldName, String newName);

    T findAndRemove(ID id);

    T findAndRemove(String key, Object value);

    T findAndRemove(Query query);

    void drop();
}
