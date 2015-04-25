package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArticleBlocked;

public interface SysArticleBlockedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysArticleBlocked record);

    int insertSelective(SysArticleBlocked record);

    SysArticleBlocked selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysArticleBlocked record);

    int updateByPrimaryKey(SysArticleBlocked record);
}