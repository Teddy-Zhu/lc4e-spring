package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysCommentTopDown;

public interface SysCommentTopDownMapper {
    int deleteByPrimaryKey(Integer intcommentid);

    int insert(SysCommentTopDown record);

    int insertSelective(SysCommentTopDown record);

    SysCommentTopDown selectByPrimaryKey(Integer intcommentid);

    int updateByPrimaryKeySelective(SysCommentTopDown record);

    int updateByPrimaryKey(SysCommentTopDown record);
}