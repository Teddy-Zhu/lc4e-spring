package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.AreaBlock;

public interface AreaBlockMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(AreaBlock record);

    int insertSelective(AreaBlock record);

    AreaBlock selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(AreaBlock record);

    int updateByPrimaryKey(AreaBlock record);
}