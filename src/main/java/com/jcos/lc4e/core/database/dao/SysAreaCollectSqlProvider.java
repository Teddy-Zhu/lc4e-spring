package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysAreaCollect;

public class SysAreaCollectSqlProvider {

    public String insertSelective(SysAreaCollect record) {
        BEGIN();
        INSERT_INTO("l4_sys_area_collected");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcollectedareaid() != null) {
            VALUES("intCollectedAreaId", "#{intcollectedareaid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysAreaCollect record) {
        BEGIN();
        UPDATE("l4_sys_area_collected");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcollectedareaid() != null) {
            SET("intCollectedAreaId = #{intcollectedareaid,jdbcType=INTEGER}");
        }
        
        if (record.getDatecreatetime() != null) {
            SET("dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}