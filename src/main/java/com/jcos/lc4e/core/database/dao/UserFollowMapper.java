package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserFollow;

public interface UserFollowMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserFollow record);

    int insertSelective(UserFollow record);

    UserFollow selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserFollow record);

    int updateByPrimaryKey(UserFollow record);
}