package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysTopic;

public class SysTopicSqlProvider {

    public String insertSelective(SysTopic record) {
        BEGIN();
        INSERT_INTO("l4_sys_topic");
        
        if (record.getInttopicid() != null) {
            VALUES("intTopicId", "#{inttopicid,jdbcType=INTEGER}");
        }
        
        if (record.getIntareaid() != null) {
            VALUES("intAreaId", "#{intareaid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getStrtopictitle() != null) {
            VALUES("strTopicTitle", "#{strtopictitle,jdbcType=VARCHAR}");
        }
        
        if (record.getStrtopicbody() != null) {
            VALUES("strTopicBody", "#{strtopicbody,jdbcType=VARCHAR}");
        }
        
        if (record.getIshide() != null) {
            VALUES("isHide", "#{ishide,jdbcType=INTEGER}");
        }
        
        if (record.getIsdeleted() != null) {
            VALUES("isDeleted", "#{isdeleted,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodified() != null) {
            VALUES("dateModified", "#{datemodified,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysTopic record) {
        BEGIN();
        UPDATE("l4_sys_topic");
        
        if (record.getIntareaid() != null) {
            SET("intAreaId = #{intareaid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getStrtopictitle() != null) {
            SET("strTopicTitle = #{strtopictitle,jdbcType=VARCHAR}");
        }
        
        if (record.getStrtopicbody() != null) {
            SET("strTopicBody = #{strtopicbody,jdbcType=VARCHAR}");
        }
        
        if (record.getIshide() != null) {
            SET("isHide = #{ishide,jdbcType=INTEGER}");
        }
        
        if (record.getIsdeleted() != null) {
            SET("isDeleted = #{isdeleted,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodified() != null) {
            SET("dateModified = #{datemodified,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intTopicId = #{inttopicid,jdbcType=INTEGER}");
        
        return SQL();
    }
}