package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.ArticleFollow;

public interface ArticleFollowMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(ArticleFollow record);

    int insertSelective(ArticleFollow record);

    ArticleFollow selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(ArticleFollow record);

    int updateByPrimaryKey(ArticleFollow record);
}