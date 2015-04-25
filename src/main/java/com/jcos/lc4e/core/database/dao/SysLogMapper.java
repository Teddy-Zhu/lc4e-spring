package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}