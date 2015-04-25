package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArticleRecord;

public interface SysArticleRecordMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysArticleRecord record);

    int insertSelective(SysArticleRecord record);

    SysArticleRecord selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysArticleRecord record);

    int updateByPrimaryKey(SysArticleRecord record);
}