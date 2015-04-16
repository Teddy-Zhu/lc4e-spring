package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Areas;

public interface AreasMapper {
    int deleteByPrimaryKey(Integer intareaid);

    int insert(Areas record);

    int insertSelective(Areas record);

    Areas selectByPrimaryKey(Integer intareaid);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
}