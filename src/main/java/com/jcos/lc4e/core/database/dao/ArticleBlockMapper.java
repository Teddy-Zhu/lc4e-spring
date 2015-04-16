package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.ArticleBlock;

public interface ArticleBlockMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(ArticleBlock record);

    int insertSelective(ArticleBlock record);

    ArticleBlock selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(ArticleBlock record);

    int updateByPrimaryKey(ArticleBlock record);
}