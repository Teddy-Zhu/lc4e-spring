package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArticleTopDown;

public interface SysArticleTopDownMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysArticleTopDown record);

    int insertSelective(SysArticleTopDown record);

    SysArticleTopDown selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysArticleTopDown record);

    int updateByPrimaryKey(SysArticleTopDown record);
}