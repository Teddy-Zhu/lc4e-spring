package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserAuthorization;

public interface UserAuthorizationMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserAuthorization record);

    int insertSelective(UserAuthorization record);

    UserAuthorization selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserAuthorization record);

    int updateByPrimaryKey(UserAuthorization record);
}