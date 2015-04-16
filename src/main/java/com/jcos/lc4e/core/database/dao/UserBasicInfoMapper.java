package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBasicInfo;

public interface UserBasicInfoMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(UserBasicInfo record);

    int insertSelective(UserBasicInfo record);

    UserBasicInfo selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(UserBasicInfo record);

    int updateByPrimaryKey(UserBasicInfo record);
}