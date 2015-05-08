package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysTopicLike;

public class SysTopicLikeSqlProvider {

    public String insertSelective(SysTopicLike record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic_like");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getInttopicid() != null) {
            VALUES("intTopicId", "#{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreate() != null) {
            VALUES("dateCreate", "#{datecreate,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopicLike record) {
        BEGIN();
        UPDATE("l4_sys_topic_like");
        
        if (record.getInttopicid() != null) {
            SET("intTopicId = #{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreate() != null) {
            SET("dateCreate = #{datecreate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}