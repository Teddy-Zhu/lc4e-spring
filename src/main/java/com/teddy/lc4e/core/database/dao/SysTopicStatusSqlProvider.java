package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysTopicStatus;

public class SysTopicStatusSqlProvider {

    public String insertSelective(SysTopicStatus record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic_status");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getInttopicid() != null) {
            VALUES("intTopicId", "#{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntstatusid() != null) {
            VALUES("intStatusId", "#{intstatusid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=DATE}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopicStatus record) {
        BEGIN();
        UPDATE("l4_sys_topic_status");
        
        if (record.getInttopicid() != null) {
            SET("intTopicId = #{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntstatusid() != null) {
            SET("intStatusId = #{intstatusid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=DATE}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}