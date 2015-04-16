package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer intuserid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer intuserid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}