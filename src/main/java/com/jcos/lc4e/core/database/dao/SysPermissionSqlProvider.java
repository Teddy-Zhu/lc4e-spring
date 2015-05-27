package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysPermission;

public class SysPermissionSqlProvider {

    public String insertSelective(SysPermission record) {
        BEGIN();
        INSERT_INTO("l4_sys_permission");
        
        if (record.getIntpermissionid() != null) {
            VALUES("intPermissionId", "#{intpermissionid,jdbcType=INTEGER}");
        }
        
        if (record.getStrpermissionabbr() != null) {
            VALUES("strPermissionAbbr", "#{strpermissionabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrpermissionname() != null) {
            VALUES("strPermissionName", "#{strpermissionname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrpermissiondescription() != null) {
            VALUES("strPermissionDescription", "#{strpermissiondescription,jdbcType=VARCHAR}");
        }
        
        if (record.getIntavaliable() != null) {
            VALUES("intAvaliable", "#{intavaliable,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysPermission record) {
        BEGIN();
        UPDATE("l4_sys_permission");
        
        if (record.getStrpermissionabbr() != null) {
            SET("strPermissionAbbr = #{strpermissionabbr,jdbcType=VARCHAR}");
        }
        
        if (record.getStrpermissionname() != null) {
            SET("strPermissionName = #{strpermissionname,jdbcType=VARCHAR}");
        }
        
        if (record.getStrpermissiondescription() != null) {
            SET("strPermissionDescription = #{strpermissiondescription,jdbcType=VARCHAR}");
        }
        
        if (record.getIntavaliable() != null) {
            SET("intAvaliable = #{intavaliable,jdbcType=INTEGER}");
        }
        
        WHERE("intPermissionId = #{intpermissionid,jdbcType=INTEGER}");
        
        return SQL();
    }
}