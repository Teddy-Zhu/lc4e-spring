package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.UserRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by teddy on 2015/6/12.
 */
public interface UserRoleRepository  extends MongoRepository<UserRole, ObjectId> {

    UserRole findByUserName(String username);
}
