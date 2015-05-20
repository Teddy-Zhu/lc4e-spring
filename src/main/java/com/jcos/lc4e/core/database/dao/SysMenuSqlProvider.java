package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysMenu;

public class SysMenuSqlProvider {

    public String insertSelective(SysMenu record) {
        BEGIN();
        INSERT_INTO("l4_sys_menu");
        
        if (record.getIntmenuid() != null) {
            VALUES("intMenuId", "#{intmenuid,jdbcType=INTEGER}");
        }
        
        if (record.getIntparentmenuid() != null) {
            VALUES("intParentMenuId", "#{intparentmenuid,jdbcType=INTEGER}");
        }
        
        if (record.getIntmenuorderid() != null) {
            VALUES("intMenuOrderId", "#{intmenuorderid,jdbcType=INTEGER}");
        }
        
        if (record.getStrmenupath() != null) {
            VALUES("strMenuPath", "#{strmenupath,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenuname() != null) {
            VALUES("strMenuName", "#{strmenuname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenucss() != null) {
            VALUES("strMenuCss", "#{strmenucss,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenuicon() != null) {
            VALUES("strMenuIcon", "#{strmenuicon,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysMenu record) {
        BEGIN();
        UPDATE("l4_sys_menu");
        
        if (record.getIntparentmenuid() != null) {
            SET("intParentMenuId = #{intparentmenuid,jdbcType=INTEGER}");
        }
        
        if (record.getIntmenuorderid() != null) {
            SET("intMenuOrderId = #{intmenuorderid,jdbcType=INTEGER}");
        }
        
        if (record.getStrmenupath() != null) {
            SET("strMenuPath = #{strmenupath,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenuname() != null) {
            SET("strMenuName = #{strmenuname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenucss() != null) {
            SET("strMenuCss = #{strmenucss,jdbcType=VARCHAR}");
        }
        
        if (record.getStrmenuicon() != null) {
            SET("strMenuIcon = #{strmenuicon,jdbcType=VARCHAR}");
        }
        
        WHERE("intMenuId = #{intmenuid,jdbcType=INTEGER}");
        
        return SQL();
    }
}