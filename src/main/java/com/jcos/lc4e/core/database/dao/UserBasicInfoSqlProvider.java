package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.UserBasicInfo;

public class UserBasicInfoSqlProvider {

    public String insertSelective(UserBasicInfo record) {
        BEGIN();
        INSERT_INTO("l4_user_basicinfo");
        
        if (record.getIntid() != null) {
            VALUES("intId", "#{intid,jdbcType=INTEGER}");
        }
        
        if (record.getIntuserid() != null) {
            VALUES("intUserId", "#{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getStrphonenumber() != null) {
            VALUES("strPhoneNumber", "#{strphonenumber,jdbcType=VARCHAR}");
        }
        
        if (record.getStrsign() != null) {
            VALUES("strSign", "#{strsign,jdbcType=VARCHAR}");
        }
        
        if (record.getStravatar() != null) {
            VALUES("strAvatar", "#{stravatar,jdbcType=VARCHAR}");
        }
        
        if (record.getIntlocationid() != null) {
            VALUES("intLocationId", "#{intlocationid,jdbcType=INTEGER}");
        }
        
        if (record.getDatebirthday() != null) {
            VALUES("dateBirthDay", "#{datebirthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatecreate() != null) {
            VALUES("dateCreate", "#{datecreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodified() != null) {
            VALUES("dateModified", "#{datemodified,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserBasicInfo record) {
        BEGIN();
        UPDATE("l4_user_basicinfo");
        
        if (record.getIntuserid() != null) {
            SET("intUserId = #{intuserid,jdbcType=INTEGER}");
        }
        
        if (record.getStrphonenumber() != null) {
            SET("strPhoneNumber = #{strphonenumber,jdbcType=VARCHAR}");
        }
        
        if (record.getStrsign() != null) {
            SET("strSign = #{strsign,jdbcType=VARCHAR}");
        }
        
        if (record.getStravatar() != null) {
            SET("strAvatar = #{stravatar,jdbcType=VARCHAR}");
        }
        
        if (record.getIntlocationid() != null) {
            SET("intLocationId = #{intlocationid,jdbcType=INTEGER}");
        }
        
        if (record.getDatebirthday() != null) {
            SET("dateBirthDay = #{datebirthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatecreate() != null) {
            SET("dateCreate = #{datecreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatemodified() != null) {
            SET("dateModified = #{datemodified,jdbcType=TIMESTAMP}");
        }
        
        WHERE("intId = #{intid,jdbcType=INTEGER}");
        
        return SQL();
    }
}