package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysAreaClosed;

public interface SysAreaClosedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysAreaClosed record);

    int insertSelective(SysAreaClosed record);

    SysAreaClosed selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysAreaClosed record);

    int updateByPrimaryKey(SysAreaClosed record);
}