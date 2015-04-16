package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.AreaFollow;

public interface AreaFollowMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(AreaFollow record);

    int insertSelective(AreaFollow record);

    AreaFollow selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(AreaFollow record);

    int updateByPrimaryKey(AreaFollow record);
}