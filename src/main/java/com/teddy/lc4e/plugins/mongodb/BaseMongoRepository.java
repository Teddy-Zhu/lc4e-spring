package com.teddy.lc4e.plugins.mongodb;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * 
 * ��MongoRepository�ӿ����Ӷ��ⳣ�õĲ���,��insert
 * 
 * @version 2013-02-26
 * 
 * @author KEN
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID>
{
	<S extends T> S insert(S entity);
	
	
	
	/**
	 *
	 * @param batchToInsert the list of Entities to insert.
	 * 
	 * @return the inserted Collection
	 */
	<S extends T> Collection<S> insert(Collection<S> batchToInsert);


	/**
	 *  find one object using key & val
	 * @param key
	 * @param value
	 * @return
	 */
	T findOne(String key, Object value);


	/**
	 * find one object using query custom
	 * @param query
	 * @return
	 */
	T findOne(Query query);

	/**
	 *  search all objects using key & val
	 * @param key
	 * @param value
	 * @return
	 */
	List<T> findAll(String key, Object value);
	

	/**
	 * ����Query��ѯʵ��
	 * @param query
	 * @return
	 */
	List<T> findAll(Query query);
	
	
	/**
	 * ����ָ��������ѯʵ��s,�����ݷ�ҳҪ�������ط�ҳ���
	 * 
	 * @param criteria
	 * @param pageable
	 * @return
	 */
	Page<T> findAll(Criteria criteria, Pageable pageable);
	

	
	/**
	 * ��������key != value �Ķ���
	 * 
	 * @param key ������
	 * @param value ����ֵ
	 * @return ��ѯ���Ķ����б�
	 */
	List<T> findAllNotEqual(String key, Object value);
	
	
	
	/**
	 * ͳ��ָ������ֵ�Ķ��������
	 * 
	 * @param key ������
	 * @param value ����ֵ
	 * @return ָ������ֵ�Ķ��������
	 */
	long count(String key, Object value);
	
	/**
	 * ͳ��ָ�������Ķ��������
	 * 
	 * @param criteria
	 * @return
	 */
	long count(Criteria criteria);
	
	/**
	 * �ж�ָ������ֵ�Ķ����Ƿ����
	 * 
	 * @param key ������
	 * @param value ����ֵ
	 * @return ָ������ֵ�Ķ����Ƿ����
	 */
	boolean exists(String key, Object value);
	

	
	
	
	/**
	 * ɾ��ָ������ֵ�����ж���
	 * 
	 * @param key ������
	 * @param value ����ֵ
	 */
	void delete(String key, Object value); 
	
	
	
	
	/**
	 * ����ID����ʵ��,��ִ�и��²���
	 * Finds the first document in the query and updates it. the old document is returned!
	 * @param id
	 * @param update 
	 * @return	
	 * 			���ظ���ǰ��ʵ��
	 */
	T findAndUpdate(ID id, Update update);

	
	/**
	 * ���ݲ�ѯ�������ҵ�һ��ʵ��,��ִ�и��²���
	 * 
	 * Finds the first document in the query and updates it. the old document is returned!
	 * 
	 * @param key   ������
	 * @param value  ����ֵ 
	 * @param update 
	 * @return 
	 * 			���ظ���ǰ��ʵ��
	 */
	T findAndUpdate(String key, Object value, Update update);
	
	
	
	/**
	 * Updates the first object that is found in the specified collection that matches the query document criteria with
	 * the provided updated document.
	 * 
	 * ����ID��ѯʵ�岢ִ�и���!
	 * 
	 * @param update the update document that contains the updated object or $ operators to manipulate the existing
	 *          object.
	 * 
	 * 
	 */
	void update(ID id, Update update);

	
	
	/**
	 * Updates the first object that is found in the specified collection that matches the query document criteria with
	 * the provided updated document.
	 * 
	 * ���ݶ��������ֵ����ʵ��,��ִ�и��²���,ע��,����������ƥ��������ÿһ��ʵ��!
	 * @param key   ������
	 * @param value  ����ֵ 
	 * 
	 * @param update the update document that contains the updated object or $ operators to manipulate the existing
	 *          object.
	 */
	void update(String key, Object value, Update update);
	
	
	
	/**
	 * Updates all objects that are found in the specified collection that matches the query document criteria with the
	 * provided updated document.
	 * 
	 * ���ݶ��������ֵ����ʵ��,��ִ�и��²���,ע��,����������ƥ��������ʵ��!
	 * @param key   ������
	 * @param value  ����ֵ 
	 * 
	 * @param update the update document that contains the updated object or $ operators to manipulate the existing
	 *          object.
	 */
	void updateMulti(String key, Object value, Update update);
	
	
	/**
	 * Updates all objects that are found in the collection for the entity class that matches the query document criteria
	 * with the provided updated document.
	 * 
	 * @param query the query document that specifies the criteria used to select a record to be updated
	 * @param update the update document that contains the updated object or $ operators to manipulate the existing
	 *          object.
	 */
	void updateMulti(Query query, Update update);
	
	//���µ����Բ���-------------------------------------------------------------------------
	
	/**
	 * Update using the $set update modifier
	 * 
	 * ��������key��ֵ 
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	void set(ID id, String key, Object value);

	
	/**
	 * Update using the $unset update modifier
	 * 
	 * ����˼�壬����ɾ���ֶ���
	 * 
	 * @param key
	 * @return
	 */
	void unset(ID id, String key);
	
	
	/**
	 * ʹָ��ID��ʵ��ĵ�ָ������key��ֵ�����ض���ֵinc
	 * 
	 * @param id
	 * @param key ����
	 * @param inc 
	 */
	void inc(ID id, String key, Number inc);


	/**
	 * Update using the $push update modifier
	 * 
	 * ��value׷�ӵ�����key(���Ա�)����ȥ��fieldһ��Ҫ�����Ա����Ͳ�������
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	void push(ID id, String key, Object value);
	
	/**
	 * Update using the $pushAll update modifier
	 * 
	 * ͬ$push,ֻ��һ�ο���׷�Ӷ��ֵ��һ�����Ա��ֶ���
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	void pushAll(ID id, String key, Object[] values);

	/**
	 * Update using the $addToSet update modifier
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	void addToSet(ID id, String key, Object value);
	
	/**
	 * Update using the $pop update modifier
	 * 
	 * ɾ�����Ա���׻�βԪ��
	 *  
	 * @param key
	 * @param pos
	 * @return
	 */
	void pop(ID id, String key, Position pos);

	/**
	 * Update using the $pull update modifier
	 * 
	 * 	�����Ա�key��ɾ��һ������valueֵ
	 * @param key
	 * @param value
	 * @return
	 */
	void pull(ID id, String key, Object value);

	/**
	 * Update using the $pullAll update modifier
	 * 
	 * ͬ$pull,����һ��ɾ�����Ա��ڵĶ��ֵ
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	 void pullAll(ID id, String key, Object[] values);

	/**
	 * Update using the $rename update modifier
	 * �޸��ֶ�oldName������ΪnewName
	 * @param oldName
	 * @param newName
	 * @return
	 */
	 void rename(ID id, String oldName, String newName);



	 
	T findAndRemove(ID id);



	T findAndRemove(String key, Object value);


	/**
	 * Map the results of an ad-hoc query on the collection for the entity type to a single instance of an object of the
	 * specified type. The first document that matches the query is returned and also removed from the collection in the
	 * database.
	 * <p/>
	 * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}.
	 * <p/>
	 * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
	 * feature rich {@link Query}.
	 * 
	 * @param query the query class that specifies the criteria used to find a record and also an optional fields
	 *          specification
	 * @return the converted object
	 */
	T findAndRemove(Query query);


	void drop();
}
