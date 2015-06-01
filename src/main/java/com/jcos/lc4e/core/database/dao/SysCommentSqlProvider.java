package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysComment;

public class SysCommentSqlProvider {

    public String insertSelective(SysComment record) {
        BEGIN();
        INSERT_INTO("l4_sys_comment");
        
        if (record.getIntcommentid() != null) {
            VALUES("intCommentId", "#{intcommentid,jdbcType=INTEGER}");
        }
        
        if (record.getInttopicid() != null) {
            VALUES("intTopicId", "#{inttopicid,jdbcType=VARCHAR}");
        }
        
        if (record.getIntcommenttitle() != null) {
            VALUES("intCommentTitle", "#{intcommenttitle,jdbcType=VARCHAR}");
        }
        
        if (record.getIntcommentbody() != null) {
            VALUES("intCommentBody", "#{intcommentbody,jdbcType=VARCHAR}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
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
        
        if (record.getDatemodifiedtime() != null) {
            VALUES("dateModifiedTime", "#{datemodifiedtime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysComment record) {
        BEGIN();
        UPDATE("l4_sys_comment");
        
        if (record.getInttopicid() != null) {
            SET("intTopicId = #{inttopicid,jdbcType=VARCHAR}");
        }
        
        if (record.getIntcommenttitle() != null) {
            SET("intCommentTitle = #{intcommenttitle,jdbcType=VARCHAR}");
        }
        
        if (record.getIntcommentbody() != null) {
            SET("intCommentBody = #{intcommentbody,jdbcType=VARCHAR}");
        }
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
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
        
        if (record.getDatemodifiedtime() != null) {
            SET("dateModifiedTime = #{datemodifiedtime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intCommentId = #{intcommentid,jdbcType=INTEGER}");
        
        return SQL();
    }
}