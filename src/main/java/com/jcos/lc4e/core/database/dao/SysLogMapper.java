package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysLog;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SysLogMapper {
    @Delete({
        "delete from l4_sys_log",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_log (intId, intLogNameId, ",
        "intUserId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intlognameid,jdbcType=INTEGER}, ",
        "#{intuserid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysLog record);

    @InsertProvider(type=SysLogSqlProvider.class, method="insertSelective")
    int insertSelective(SysLog record);

    @Select({
        "select",
        "intId, intLogNameId, intUserId, dateCreateTime",
        "from l4_sys_log",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysLog selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysLog record);

    @Update({
        "update l4_sys_log",
        "set intLogNameId = #{intlognameid,jdbcType=INTEGER},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysLog record);
}