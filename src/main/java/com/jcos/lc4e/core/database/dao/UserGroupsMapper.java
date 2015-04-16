package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserGroups;

public interface UserGroupsMapper {
    int deleteByPrimaryKey(Integer intusergroupid);

    int insert(UserGroups record);

    int insertSelective(UserGroups record);

    UserGroups selectByPrimaryKey(Integer intusergroupid);

    int updateByPrimaryKeySelective(UserGroups record);

    int updateByPrimaryKey(UserGroups record);
}