package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysArea;

public class SysAreaSqlProvider {

    public String insertSelective(SysArea record) {
        BEGIN();
        INSERT_INTO("l4_sys_area");
        
        if (record.getIntareaid() != null) {
            VALUES("intAreaId", "#{intareaid,jdbcType=INTEGER}");
        }
        
        if (record.getIntparentareaid() != null) {
            VALUES("intParentAreaId", "#{intparentareaid,jdbcType=INTEGER}");
        }
        
        if (record.getStrareaabbr() != null) {
            VALUES("strAreaAbbr", "#{strareaabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareaname() != null) {
            VALUES("strAreaName", "#{strareaname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareadescription() != null) {
            VALUES("strAreaDescription", "#{strareadescription,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareacss() != null) {
            VALUES("strAreaCss", "#{strareacss,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareaicon() != null) {
            VALUES("strAreaIcon", "#{strareaicon,jdbcType=VARCHAR}");
        }
        
        if (record.getIsshow() != null) {
            VALUES("isShow", "#{isshow,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysArea record) {
        BEGIN();
        UPDATE("l4_sys_area");
        
        if (record.getIntparentareaid() != null) {
            SET("intParentAreaId = #{intparentareaid,jdbcType=INTEGER}");
        }
        
        if (record.getStrareaabbr() != null) {
            SET("strAreaAbbr = #{strareaabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareaname() != null) {
            SET("strAreaName = #{strareaname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareadescription() != null) {
            SET("strAreaDescription = #{strareadescription,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareacss() != null) {
            SET("strAreaCss = #{strareacss,jdbcType=VARCHAR}");
        }
        
        if (record.getStrareaicon() != null) {
            SET("strAreaIcon = #{strareaicon,jdbcType=VARCHAR}");
        }
        
        if (record.getIsshow() != null) {
            SET("isShow = #{isshow,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intAreaId = #{intareaid,jdbcType=INTEGER}");
        
        return SQL();
    }
}