package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysLogName;

public class SysLogNameSqlProvider {

    public String insertSelective(SysLogName record) {
        BEGIN();
        INSERT_INTO("l4_sys_log_name");
        
        if (record.getIntlognameid() != null) {
            VALUES("intLogNameId", "#{intlognameid,jdbcType=INTEGER}");
        }
        
        if (record.getStrlognameabbr() != null) {
            VALUES("strLogNameAbbr", "#{strlognameabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrlogname() != null) {
            VALUES("strLogName", "#{strlogname,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysLogName record) {
        BEGIN();
        UPDATE("l4_sys_log_name");
        
        if (record.getStrlognameabbr() != null) {
            SET("strLogNameAbbr = #{strlognameabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrlogname() != null) {
            SET("strLogName = #{strlogname,jdbcType=VARCHAR}");
        }
        
        WHERE("intLogNameId = #{intlognameid,jdbcType=INTEGER}");
        
        return SQL();
    }
}