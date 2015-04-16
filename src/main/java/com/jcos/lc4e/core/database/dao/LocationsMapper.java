package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Locations;

public interface LocationsMapper {
    int deleteByPrimaryKey(Integer intlocationid);

    int insert(Locations record);

    int insertSelective(Locations record);

    Locations selectByPrimaryKey(Integer intlocationid);

    int updateByPrimaryKeySelective(Locations record);

    int updateByPrimaryKey(Locations record);
}