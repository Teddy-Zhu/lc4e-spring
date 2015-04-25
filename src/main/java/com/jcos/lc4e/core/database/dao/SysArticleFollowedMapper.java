package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArticleFollowed;

public interface SysArticleFollowedMapper {
    int deleteByPrimaryKey(Integer intid);

    int insert(SysArticleFollowed record);

    int insertSelective(SysArticleFollowed record);

    SysArticleFollowed selectByPrimaryKey(Integer intid);

    int updateByPrimaryKeySelective(SysArticleFollowed record);

    int updateByPrimaryKey(SysArticleFollowed record);
}