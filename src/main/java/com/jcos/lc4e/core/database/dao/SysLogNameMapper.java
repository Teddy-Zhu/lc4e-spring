package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysLogName;

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

public interface SysLogNameMapper {
    @Delete({
        "delete from l4_sys_log_name",
        "where intLogNameId = #{intlognameid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intlognameid);

    @Insert({
        "insert into l4_sys_log_name (intLogNameId, strLogNameAbbr, ",
        "strLogName)",
        "values (#{intlognameid,jdbcType=INTEGER}, #{strlognameabbr,jdbcType=VARCHAR}, ",
        "#{strlogname,jdbcType=VARCHAR})"
    })
    int insert(SysLogName record);

    @InsertProvider(type=SysLogNameSqlProvider.class, method="insertSelective")
    int insertSelective(SysLogName record);

    @Select({
        "select",
        "intLogNameId, strLogNameAbbr, strLogName",
        "from l4_sys_log_name",
        "where intLogNameId = #{intlognameid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysLogName selectByPrimaryKey(Integer intlognameid);

    @UpdateProvider(type=SysLogNameSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysLogName record);

    @Update({
        "update l4_sys_log_name",
        "set strLogNameAbbr = #{strlognameabbr,jdbcType=VARCHAR},",
          "strLogName = #{strlogname,jdbcType=VARCHAR}",
        "where intLogNameId = #{intlognameid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysLogName record);
}