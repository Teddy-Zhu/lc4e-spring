package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBlocked;

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

public interface UserBlockedMapper {
    @Delete({
        "delete from l4_user_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_user_blocked (intId, intUserId, ",
        "intBlockedUserId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intblockeduserid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(UserBlocked record);

    @InsertProvider(type=UserBlockedSqlProvider.class, method="insertSelective")
    int insertSelective(UserBlocked record);

    @Select({
        "select",
        "intId, intUserId, intBlockedUserId, dateCreateTime",
        "from l4_user_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserBlocked selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=UserBlockedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserBlocked record);

    @Update({
        "update l4_user_blocked",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intBlockedUserId = #{intblockeduserid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserBlocked record);
}