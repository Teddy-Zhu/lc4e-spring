package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysAreaClose;

public class SysAreaCloseSqlProvider {

    public String insertSelective(SysAreaClose record) {
        BEGIN();
        INSERT_INTO("l4_sys_area_closed");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntareaid() != null) {
            VALUES("intAreaId", "#{intareaid,jdbcType=INTEGER}");
        }
        
        if (record.getIntusergroupid() != null) {
            VALUES("intUserGroupId", "#{intusergroupid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysAreaClose record) {
        BEGIN();
        UPDATE("l4_sys_area_closed");
        
        if (record.getIntareaid() != null) {
            SET("intAreaId = #{intareaid,jdbcType=INTEGER}");
        }
        
        if (record.getIntusergroupid() != null) {
            SET("intUserGroupId = #{intusergroupid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}