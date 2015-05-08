package com.jcos.lc4e.core.database.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.jcos.lc4e.core.database.model.SysAreaBlocked;

public class SysAreaBlockedSqlProvider {

	public String insertSelective(SysAreaBlocked record) {
		BEGIN();
		INSERT_INTO("l4_sys_area_blocked");

		if (record.getIntid() != null) {
			VALUES("intId", "#{intid,jdbcType=INTEGER}");
		}

		if (record.getIntblockedareaid() != null) {
			VALUES("intBlockedAreaId", "#{intblockedareaid,jdbcType=INTEGER}");
		}

		if (record.getIntusergroupid() != null) {
			VALUES("intUserGroupId", "#{intusergroupid,jdbcType=INTEGER}");
		}

		if (record.getDatecreatetime() != null) {
			VALUES("dateCreateTime", "#{datecreatetime,jdbcType=TIMESTAMP}");
		}

		return SQL();
	}

	public String updateByPrimaryKeySelective(SysAreaBlocked record) {
		BEGIN();
		UPDATE("l4_sys_area_blocked");

		if (record.getIntblockedareaid() != null) {
			SET("intBlockedAreaId = #{intblockedareaid,jdbcType=INTEGER}");
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