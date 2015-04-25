package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysComment;

public interface SysCommentMapper {
    int deleteByPrimaryKey(Integer intcommentid);

    int insert(SysComment record);

    int insertSelective(SysComment record);

    SysComment selectByPrimaryKey(Integer intcommentid);

    int updateByPrimaryKeySelective(SysComment record);

    int updateByPrimaryKey(SysComment record);
}