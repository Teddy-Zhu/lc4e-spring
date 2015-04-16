package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.AreaClose;

public interface AreaCloseMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(AreaClose record);

    int insertSelective(AreaClose record);

    AreaClose selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(AreaClose record);

    int updateByPrimaryKey(AreaClose record);
}