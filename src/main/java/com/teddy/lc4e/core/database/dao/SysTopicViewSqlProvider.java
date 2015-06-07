package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysTopicView;

public class SysTopicViewSqlProvider {

    public String insertSelective(SysTopicView record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic_view");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getInttopicid() != null) {
            VALUES("intTopicId", "#{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntviewcount() != null) {
            VALUES("intViewCount", "#{intviewcount,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopicView record) {
        BEGIN();
        UPDATE("l4_sys_topic_view");
        
        if (record.getInttopicid() != null) {
            SET("intTopicId = #{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntviewcount() != null) {
            SET("intViewCount = #{intviewcount,jdbcType=INTEGER}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}