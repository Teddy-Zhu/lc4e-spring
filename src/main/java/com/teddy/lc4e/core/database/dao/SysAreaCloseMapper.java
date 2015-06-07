package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysAreaClose;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysAreaCloseMapper {
    @Delete({
        "delete from l4_sys_area_closed",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_area_closed (intId, intAreaId, ",
        "intUserGroupId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intareaid,jdbcType=INTEGER}, ",
        "#{intusergroupid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysAreaClose record);

    @InsertProvider(type=SysAreaCloseSqlProvider.class, method="insertSelective")
    int insertSelective(SysAreaClose record);

    @Select({
        "select",
        "intId, intAreaId, intUserGroupId, dateCreateTime",
        "from l4_sys_area_closed",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysAreaClose selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysAreaCloseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysAreaClose record);

    @Update({
        "update l4_sys_area_closed",
        "set intAreaId = #{intareaid,jdbcType=INTEGER},",
          "intUserGroupId = #{intusergroupid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysAreaClose record);
}