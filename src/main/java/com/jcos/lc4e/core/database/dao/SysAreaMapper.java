package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArea;

public interface SysAreaMapper {
    int deleteByPrimaryKey(Integer intareaid);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    SysArea selectByPrimaryKey(Integer intareaid);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);
}