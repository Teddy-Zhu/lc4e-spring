package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysRolePermission;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysRolePermissionMapper {
    @Delete({
        "delete from l4_sys_role_permission",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_role_permission (intId, intRoleId, ",
        "intPermissionId, dateCreated)",
        "values (#{intid,jdbcType=INTEGER}, #{introleid,jdbcType=INTEGER}, ",
        "#{intpermissionid,jdbcType=INTEGER}, #{datecreated,jdbcType=TIMESTAMP})"
    })
    int insert(SysRolePermission record);

    @InsertProvider(type=SysRolePermissionSqlProvider.class, method="insertSelective")
    int insertSelective(SysRolePermission record);

    @Select({
        "select",
        "intId, intRoleId, intPermissionId, dateCreated",
        "from l4_sys_role_permission",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysRolePermission selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysRolePermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysRolePermission record);

    @Update({
        "update l4_sys_role_permission",
        "set intRoleId = #{introleid,jdbcType=INTEGER},",
          "intPermissionId = #{intpermissionid,jdbcType=INTEGER},",
          "dateCreated = #{datecreated,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysRolePermission record);
}