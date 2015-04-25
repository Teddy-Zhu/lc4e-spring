package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysAreaFollowed;

public interface SysAreaFollowedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysAreaFollowed record);

    int insertSelective(SysAreaFollowed record);

    SysAreaFollowed selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysAreaFollowed record);

    int updateByPrimaryKey(SysAreaFollowed record);
}