package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysUserMessage;

public class SysUserMessageSqlProvider {

    public String insertSelective(SysUserMessage record) {
        BEGIN();
        INSERT_INTO("l4_sys_user_message");
        
        if (record.getIntmessageid() != null) {
            VALUES("intMessageId", "#{intmessageid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntdestuserid() != null) {
            VALUES("intDestUserId", "#{intdestuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntread() != null) {
            VALUES("intRead", "#{intread,jdbcType=INTEGER}");
        }
        
        if (record.getStrmessagetitle() != null) {
            VALUES("strMessageTitle", "#{strmessagetitle,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmessagebody() != null) {
            VALUES("strMessageBody", "#{strmessagebody,jdbcType=VARCHAR}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodifiedtime() != null) {
            VALUES("dateModifiedTime", "#{datemodifiedtime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysUserMessage record) {
        BEGIN();
        UPDATE("l4_sys_user_message");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntdestuserid() != null) {
            SET("intDestUserId = #{intdestuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntread() != null) {
            SET("intRead = #{intread,jdbcType=INTEGER}");
        }
        
        if (record.getStrmessagetitle() != null) {
            SET("strMessageTitle = #{strmessagetitle,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmessagebody() != null) {
            SET("strMessageBody = #{strmessagebody,jdbcType=VARCHAR}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodifiedtime() != null) {
            SET("dateModifiedTime = #{datemodifiedtime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intMessageId = #{intmessageid,jdbcType=INTEGER}");
        
        return SQL();
    }
}