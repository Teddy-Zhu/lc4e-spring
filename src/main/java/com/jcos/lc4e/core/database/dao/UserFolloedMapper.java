package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserFolloed;

public interface UserFolloedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserFolloed record);

    int insertSelective(UserFolloed record);

    UserFolloed selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserFolloed record);

    int updateByPrimaryKey(UserFolloed record);
}