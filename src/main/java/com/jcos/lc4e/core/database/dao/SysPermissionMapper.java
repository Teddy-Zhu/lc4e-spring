package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer intpermissionid);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer intpermissionid);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}