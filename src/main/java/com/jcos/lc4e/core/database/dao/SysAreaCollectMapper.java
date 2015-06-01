package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysAreaCollect;

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

public interface SysAreaCollectMapper {
    @Delete({
        "delete from l4_sys_area_collected",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_area_collected (intId, intUserId, ",
        "intCollectedAreaId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intcollectedareaid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysAreaCollect record);

    @InsertProvider(type=SysAreaCollectSqlProvider.class, method="insertSelective")
    int insertSelective(SysAreaCollect record);

    @Select({
        "select",
        "intId, intUserId, intCollectedAreaId, dateCreateTime",
        "from l4_sys_area_collected",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysAreaCollect selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysAreaCollectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysAreaCollect record);

    @Update({
        "update l4_sys_area_collected",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intCollectedAreaId = #{intcollectedareaid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysAreaCollect record);
}