package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBlocked;

public interface UserBlockedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserBlocked record);

    int insertSelective(UserBlocked record);

    UserBlocked selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserBlocked record);

    int updateByPrimaryKey(UserBlocked record);
}