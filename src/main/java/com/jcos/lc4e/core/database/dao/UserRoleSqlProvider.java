package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.UserRole;

public class UserRoleSqlProvider {

    public String insertSelective(UserRole record) {
        BEGIN();
        INSERT_INTO("l4_user_role");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntroleid() != null) {
            VALUES("intRoleId", "#{introleid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreated() != null) {
            VALUES("dateCreated", "#{datecreated,jdbcType=DATE}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserRole record) {
        BEGIN();
        UPDATE("l4_user_role");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntroleid() != null) {
            SET("intRoleId = #{introleid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreated() != null) {
            SET("dateCreated = #{datecreated,jdbcType=DATE}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}