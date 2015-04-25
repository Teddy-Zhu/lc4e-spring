package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer intmenuid);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer intmenuid);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}