package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.LogName;

public interface LogNameMapper {
    int deleteByPrimaryKey(Integer intlognameid);

    int insert(LogName record);

    int insertSelective(LogName record);

    LogName selectByPrimaryKey(Integer intlognameid);

    int updateByPrimaryKeySelective(LogName record);

    int updateByPrimaryKey(LogName record);
}