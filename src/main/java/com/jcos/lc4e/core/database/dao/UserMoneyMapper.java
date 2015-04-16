package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserMoney;

public interface UserMoneyMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserMoney record);

    int insertSelective(UserMoney record);

    UserMoney selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserMoney record);

    int updateByPrimaryKey(UserMoney record);
}