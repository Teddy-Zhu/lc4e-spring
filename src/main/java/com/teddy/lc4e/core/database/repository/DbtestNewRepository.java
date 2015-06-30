package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.Dbtest;
import com.teddy.lc4e.plugins.mongodb.BaseMongoRepository;
import org.bson.types.ObjectId;

/**
 * Created by teddy on 2015/6/25.
 */
public interface DbtestNewRepository extends BaseMongoRepository<Dbtest, ObjectId> {

    Dbtest findByName(String name);
}
