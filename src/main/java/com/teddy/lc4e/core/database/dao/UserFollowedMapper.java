package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.UserFollowed;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface UserFollowedMapper {
    @Delete({
        "delete from l4_user_followed",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_user_followed (intId, intUserId, ",
        "intFollowedUserId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intfolloweduserid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(UserFollowed record);

    @InsertProvider(type=UserFollowedSqlProvider.class, method="insertSelective")
    int insertSelective(UserFollowed record);

    @Select({
        "select",
        "intId, intUserId, intFollowedUserId, dateCreateTime",
        "from l4_user_followed",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserFollowed selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=UserFollowedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserFollowed record);

    @Update({
        "update l4_user_followed",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intFollowedUserId = #{intfolloweduserid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserFollowed record);
}