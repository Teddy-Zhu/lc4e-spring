package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserRole;

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

public interface UserRoleMapper {
    @Delete({
        "delete from l4_user_role",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_user_role (intId, intUserId, ",
        "intRoleId, dateCreated)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{introleid,jdbcType=INTEGER}, #{datecreated,jdbcType=DATE})"
    })
    int insert(UserRole record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserRole record);

    @Select({
        "select",
        "intId, intUserId, intRoleId, dateCreated",
        "from l4_user_role",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserRole selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRole record);

    @Update({
        "update l4_user_role",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intRoleId = #{introleid,jdbcType=INTEGER},",
          "dateCreated = #{datecreated,jdbcType=DATE}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRole record);
}