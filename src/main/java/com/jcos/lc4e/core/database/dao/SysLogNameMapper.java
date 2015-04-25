package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysLogName;

public interface SysLogNameMapper {
    int deleteByPrimaryKey(Integer intlognameid);

    int insert(SysLogName record);

    int insertSelective(SysLogName record);

    SysLogName selectByPrimaryKey(Integer intlognameid);

    int updateByPrimaryKeySelective(SysLogName record);

    int updateByPrimaryKey(SysLogName record);
}