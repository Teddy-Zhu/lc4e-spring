package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysRolePermission;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}