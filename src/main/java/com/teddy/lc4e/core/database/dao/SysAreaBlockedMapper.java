package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysAreaBlocked;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysAreaBlockedMapper {
    @Delete({
        "delete from l4_sys_area_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_area_blocked (intId, intBlockedAreaId, ",
        "intUserGroupId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intblockedareaid,jdbcType=INTEGER}, ",
        "#{intusergroupid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysAreaBlocked record);

    @InsertProvider(type=SysAreaBlockedSqlProvider.class, method="insertSelective")
    int insertSelective(SysAreaBlocked record);

    @Select({
        "select",
        "intId, intBlockedAreaId, intUserGroupId, dateCreateTime",
        "from l4_sys_area_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysAreaBlocked selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysAreaBlockedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysAreaBlocked record);

    @Update({
        "update l4_sys_area_blocked",
        "set intBlockedAreaId = #{intblockedareaid,jdbcType=INTEGER},",
          "intUserGroupId = #{intusergroupid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysAreaBlocked record);
}