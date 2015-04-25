package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysMessage;

public interface SysMessageMapper {
    int deleteByPrimaryKey(Integer intmessageid);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    SysMessage selectByPrimaryKey(Integer intmessageid);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
}