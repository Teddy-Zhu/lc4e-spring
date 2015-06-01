package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.User;

public class UserSqlProvider {

    public String insertSelective(User record) {
        BEGIN();
        INSERT_INTO("l4_user");
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getStrusername() != null) {
            VALUES("strUserName", "#{strusername,jdbcType=VARCHAR}");
        }
        
        if (record.getStrusermail() != null) {
            VALUES("strUserMail", "#{strusermail,jdbcType=VARCHAR}");
        }
        
        if (record.getStrusernick() != null) {
            VALUES("strUserNick", "#{strusernick,jdbcType=VARCHAR}");
        }
        
        if (record.getStruserpass() != null) {
            VALUES("strUserPass", "#{struserpass,jdbcType=VARCHAR}");
        }
        
        if (record.getStruserpasssalt() != null) {
            VALUES("strUserPassSalt", "#{struserpasssalt,jdbcType=VARCHAR}");
        }
        
        if (record.getIntlocked() != null) {
            VALUES("intLocked", "#{intlocked,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(User record) {
        BEGIN();
        UPDATE("l4_user");
        
        if (record.getStrusername() != null) {
            SET("strUserName = #{strusername,jdbcType=VARCHAR}");
        }
        
        if (record.getStrusermail() != null) {
            SET("strUserMail = #{strusermail,jdbcType=VARCHAR}");
        }
        
        if (record.getStrusernick() != null) {
            SET("strUserNick = #{strusernick,jdbcType=VARCHAR}");
        }
        
        if (record.getStruserpass() != null) {
            SET("strUserPass = #{struserpass,jdbcType=VARCHAR}");
        }
        
        if (record.getStruserpasssalt() != null) {
            SET("strUserPassSalt = #{struserpasssalt,jdbcType=VARCHAR}");
        }
        
        if (record.getIntlocked() != null) {
            SET("intLocked = #{intlocked,jdbcType=INTEGER}");
        }
        
        WHERE("intUserId = #{intuserid,jdbcType=INTEGER}");
        
        return SQL();
    }
}