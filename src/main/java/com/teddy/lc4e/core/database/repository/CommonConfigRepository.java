package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.SysComVar;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by teddy on 2015/6/14.
 */
public interface CommonConfigRepository extends MongoRepository<SysComVar, ObjectId> {

    SysComVar findByName(String comConfigName);

    List<SysComVar> findByNameIn(String[] comConfigNames);

    List<SysComVar> findByIdIn(ObjectId[] ids);
}
