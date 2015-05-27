package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysTopicStatusName;

public class SysTopicStatusNameSqlProvider {

    public String insertSelective(SysTopicStatusName record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic_status_name");
        
        if (record.getIntstatusid() != null) {
            VALUES("intStatusId", "#{intstatusid,jdbcType=INTEGER}");
        }
        
        if (record.getStrstatusabbr() != null) {
            VALUES("strStatusAbbr", "#{strstatusabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusname() != null) {
            VALUES("strStatusName", "#{strstatusname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusdesription() != null) {
            VALUES("strStatusDesription", "#{strstatusdesription,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusicon() != null) {
            VALUES("strStatusIcon", "#{strstatusicon,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopicStatusName record) {
        BEGIN();
        UPDATE("l4_sys_topic_status_name");
        
        if (record.getStrstatusabbr() != null) {
            SET("strStatusAbbr = #{strstatusabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusname() != null) {
            SET("strStatusName = #{strstatusname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusdesription() != null) {
            SET("strStatusDesription = #{strstatusdesription,jdbcType=VARCHAR}");
        }
        
        if (record.getStrstatusicon() != null) {
            SET("strStatusIcon = #{strstatusicon,jdbcType=VARCHAR}");
        }
        
        WHERE("intStatusId = #{intstatusid,jdbcType=INTEGER}");
        
        return SQL();
    }
}