package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysAreaBlocked;

public interface SysAreaBlockedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysAreaBlocked record);

    int insertSelective(SysAreaBlocked record);

    SysAreaBlocked selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysAreaBlocked record);

    int updateByPrimaryKey(SysAreaBlocked record);
}