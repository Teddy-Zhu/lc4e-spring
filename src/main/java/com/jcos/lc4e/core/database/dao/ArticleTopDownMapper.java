package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.ArticleTopDown;

public interface ArticleTopDownMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(ArticleTopDown record);

    int insertSelective(ArticleTopDown record);

    ArticleTopDown selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(ArticleTopDown record);

    int updateByPrimaryKey(ArticleTopDown record);
}