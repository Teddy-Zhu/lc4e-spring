package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBlock;

public interface UserBlockMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserBlock record);

    int insertSelective(UserBlock record);

    UserBlock selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserBlock record);

    int updateByPrimaryKey(UserBlock record);
}