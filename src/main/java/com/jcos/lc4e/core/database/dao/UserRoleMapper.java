package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}