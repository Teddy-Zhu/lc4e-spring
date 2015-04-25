package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArticle;

public interface SysArticleMapper {
    int deleteByPrimaryKey(Integer intarticleid);

    int insert(SysArticle record);

    int insertSelective(SysArticle record);

    SysArticle selectByPrimaryKey(Integer intarticleid);

    int updateByPrimaryKeySelective(SysArticle record);

    int updateByPrimaryKey(SysArticle record);
}