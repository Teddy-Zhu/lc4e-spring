package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Menus;

public interface MenusMapper {
    int deleteByPrimaryKey(Integer intmenuid);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Integer intmenuid);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}