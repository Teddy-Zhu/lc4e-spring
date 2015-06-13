package com.teddy.lc4e.core.database.repository;

import com.teddy.lc4e.core.database.model.SysRolePermission;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

/**
 * Created by teddy on 2015/6/12.
 */
public interface RolePermissionRepoistory extends MongoRepository<SysRolePermission, ObjectId> {

    SysRolePermission findBySysRoleRoleAbbrIn(Set<String> roles);
}
