package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysTopicCollected;

public class SysTopicCollectedSqlProvider {

    public String insertSelective(SysTopicCollected record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic_collected");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcollectedtopicid() != null) {
            VALUES("intCollectedTopicId", "#{intcollectedtopicid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=DATE}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopicCollected record) {
        BEGIN();
        UPDATE("l4_sys_topic_collected");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcollectedtopicid() != null) {
            SET("intCollectedTopicId = #{intcollectedtopicid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=DATE}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}