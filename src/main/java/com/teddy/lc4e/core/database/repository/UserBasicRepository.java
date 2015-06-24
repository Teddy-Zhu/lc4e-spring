package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.UserBasicInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by teddy on 2015/6/24.
 */
public interface UserBasicRepository extends MongoRepository<UserBasicInfo, ObjectId> {
}
