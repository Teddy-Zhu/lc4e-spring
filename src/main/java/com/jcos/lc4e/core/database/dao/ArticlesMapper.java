package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Articles;

public interface ArticlesMapper {
    int deleteByPrimaryKey(Integer intarticleid);

    int insert(Articles record);

    int insertSelective(Articles record);

    Articles selectByPrimaryKey(Integer intarticleid);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKey(Articles record);
}