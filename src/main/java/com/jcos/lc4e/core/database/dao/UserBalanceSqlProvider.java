package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.UserBalance;

public class UserBalanceSqlProvider {

    public String insertSelective(UserBalance record) {
        BEGIN();
        INSERT_INTO("l4_user_balance");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDoubalance() != null) {
            VALUES("douBalance", "#{doubalance,jdbcType=DECIMAL}");
        }
        
        if (record.getDatemodifiedtime() != null) {
            VALUES("dateModifiedTime", "#{datemodifiedtime,jdbcType=DATE}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserBalance record) {
        BEGIN();
        UPDATE("l4_user_balance");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getDoubalance() != null) {
            SET("douBalance = #{doubalance,jdbcType=DECIMAL}");
        }
        
        if (record.getDatemodifiedtime() != null) {
            SET("dateModifiedTime = #{datemodifiedtime,jdbcType=DATE}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}