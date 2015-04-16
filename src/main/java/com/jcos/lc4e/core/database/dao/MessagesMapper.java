package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.Messages;

public interface MessagesMapper {
    int deleteByPrimaryKey(Integer intmessageid);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Integer intmessageid);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);
}