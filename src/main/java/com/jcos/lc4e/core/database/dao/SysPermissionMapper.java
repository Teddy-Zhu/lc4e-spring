package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysPermission;

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

public interface SysPermissionMapper {
    @Delete({
        "delete from l4_sys_permission",
        "where intPermissionId = #{intpermissionid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intpermissionid);

    @Insert({
        "insert into l4_sys_permission (intPermissionId, strPermissionAbbr, ",
        "strPermissionName, strPermissionDescription, ",
        "intAvaliable)",
        "values (#{intpermissionid,jdbcType=INTEGER}, #{strpermissionabbr,jdbcType=VARCHAR}, ",
        "#{strpermissionname,jdbcType=VARCHAR}, #{strpermissiondescription,jdbcType=VARCHAR}, ",
        "#{intavaliable,jdbcType=INTEGER})"
    })
    int insert(SysPermission record);

    @InsertProvider(type=SysPermissionSqlProvider.class, method="insertSelective")
    int insertSelective(SysPermission record);

    @Select({
        "select",
        "intPermissionId, strPermissionAbbr, strPermissionName, strPermissionDescription, ",
        "intAvaliable",
        "from l4_sys_permission",
        "where intPermissionId = #{intpermissionid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysPermission selectByPrimaryKey(Integer intpermissionid);

    @UpdateProvider(type=SysPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysPermission record);

    @Update({
        "update l4_sys_permission",
        "set strPermissionAbbr = #{strpermissionabbr,jdbcType=VARCHAR},",
          "strPermissionName = #{strpermissionname,jdbcType=VARCHAR},",
          "strPermissionDescription = #{strpermissiondescription,jdbcType=VARCHAR},",
          "intAvaliable = #{intavaliable,jdbcType=INTEGER}",
        "where intPermissionId = #{intpermissionid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysPermission record);
}