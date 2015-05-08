package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysUserLocations;

public class SysUserLocationsSqlProvider {

    public String insertSelective(SysUserLocations record) {
        BEGIN();
        INSERT_INTO("l4_sys_user_locations");
        
        if (record.getIntlocationid() != null) {
            VALUES("intLocationId", "#{intlocationid,jdbcType=INTEGER}");
        }
        
        if (record.getIntprovinceid() != null) {
            VALUES("intProvinceId", "#{intprovinceid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcityid() != null) {
            VALUES("intCityId", "#{intcityid,jdbcType=INTEGER}");
        }
        
        if (record.getIntareaid() != null) {
            VALUES("intAreaId", "#{intareaid,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysUserLocations record) {
        BEGIN();
        UPDATE("l4_sys_user_locations");
        
        if (record.getIntprovinceid() != null) {
            SET("intProvinceId = #{intprovinceid,jdbcType=INTEGER}");
        }
        
        if (record.getIntcityid() != null) {
            SET("intCityId = #{intcityid,jdbcType=INTEGER}");
        }
        
        if (record.getIntareaid() != null) {
            SET("intAreaId = #{intareaid,jdbcType=INTEGER}");
        }
        
        WHERE("intLocationId = #{intlocationid,jdbcType=INTEGER}");
        
        return SQL();
    }
}