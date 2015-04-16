package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.CommentTopDown;

public interface CommentTopDownMapper {
    int deleteByPrimaryKey(Integer intcommentid);

    int insert(CommentTopDown record);

    int insertSelective(CommentTopDown record);

    CommentTopDown selectByPrimaryKey(Integer intcommentid);

    int updateByPrimaryKeySelective(CommentTopDown record);

    int updateByPrimaryKey(CommentTopDown record);
}