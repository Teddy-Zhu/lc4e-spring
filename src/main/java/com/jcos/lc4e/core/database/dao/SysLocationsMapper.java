package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysLocations;

public interface SysLocationsMapper {
    int deleteByPrimaryKey(Integer intlocationid);

    int insert(SysLocations record);

    int insertSelective(SysLocations record);

    SysLocations selectByPrimaryKey(Integer intlocationid);

    int updateByPrimaryKeySelective(SysLocations record);

    int updateByPrimaryKey(SysLocations record);
}