package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.SysMenu;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by teddy on 2015/6/12.
 */
public interface MenuRepository extends MongoRepository<SysMenu, ObjectId> {


}
