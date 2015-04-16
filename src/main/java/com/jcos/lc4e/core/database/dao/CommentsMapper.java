package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Comments;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer intcommentid);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer intcommentid);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
}