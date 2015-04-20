package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.ArticleRecord;

public interface ArticleRecordMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(ArticleRecord record);

    int insertSelective(ArticleRecord record);

    ArticleRecord selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(ArticleRecord record);

    int updateByPrimaryKey(ArticleRecord record);
}