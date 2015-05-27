package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysUserMessage;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SysUserMessageMapper {
    @Delete({
        "delete from l4_sys_user_message",
        "where intMessageId = #{intmessageid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intmessageid);

    @Insert({
        "insert into l4_sys_user_message (intMessageId, intUserId, ",
        "intDestUserId, intRead, ",
        "strMessageTitle, strMessageBody, ",
        "dateCreateTime, dateModifiedTime)",
        "values (#{intmessageid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intdestuserid,jdbcType=INTEGER}, #{intread,jdbcType=INTEGER}, ",
        "#{strmessagetitle,jdbcType=VARCHAR}, #{strmessagebody,jdbcType=VARCHAR}, ",
        "#{datecreatetime,jdbcType=TIMESTAMP}, #{datemodifiedtime,jdbcType=TIMESTAMP})"
    })
    int insert(SysUserMessage record);

    @InsertProvider(type=SysUserMessageSqlProvider.class, method="insertSelective")
    int insertSelective(SysUserMessage record);

    @Select({
        "select",
        "intMessageId, intUserId, intDestUserId, intRead, strMessageTitle, strMessageBody, ",
        "dateCreateTime, dateModifiedTime",
        "from l4_sys_user_message",
        "where intMessageId = #{intmessageid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysUserMessage selectByPrimaryKey(Integer intmessageid);

    @UpdateProvider(type=SysUserMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserMessage record);

    @Update({
        "update l4_sys_user_message",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intDestUserId = #{intdestuserid,jdbcType=INTEGER},",
          "intRead = #{intread,jdbcType=INTEGER},",
          "strMessageTitle = #{strmessagetitle,jdbcType=VARCHAR},",
          "strMessageBody = #{strmessagebody,jdbcType=VARCHAR},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP},",
          "dateModifiedTime = #{datemodifiedtime,jdbcType=TIMESTAMP}",
        "where intMessageId = #{intmessageid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUserMessage record);
}