package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBalance;

public interface UserBalanceMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserBalance record);

    int insertSelective(UserBalance record);

    UserBalance selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserBalance record);

    int updateByPrimaryKey(UserBalance record);
}