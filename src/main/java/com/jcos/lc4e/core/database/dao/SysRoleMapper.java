package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer introleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer introleid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}