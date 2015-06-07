package com.teddy.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.teddy.lc4e.core.database.model.SysRolePermission;

public class SysRolePermissionSqlProvider {

    public String insertSelective(SysRolePermission record) {
        BEGIN();
        INSERT_INTO("l4_sys_role_permission");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntroleid() != null) {
            VALUES("intRoleId", "#{introleid,jdbcType=INTEGER}");
        }
        
        if (record.getIntpermissionid() != null) {
            VALUES("intPermissionId", "#{intpermissionid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreated() != null) {
            VALUES("dateCreated", "#{datecreated,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysRolePermission record) {
        BEGIN();
        UPDATE("l4_sys_role_permission");
        
        if (record.getIntroleid() != null) {
            SET("intRoleId = #{introleid,jdbcType=INTEGER}");
        }
        
        if (record.getIntpermissionid() != null) {
            SET("intPermissionId = #{intpermissionid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreated() != null) {
            SET("dateCreated = #{datecreated,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}