package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBalance;

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

public interface UserBalanceMapper {
    @Delete({
        "delete from l4_user_balance",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_user_balance (intId, intUserId, ",
        "douBalance, dateModifiedTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{doubalance,jdbcType=DECIMAL}, #{datemodifiedtime,jdbcType=DATE})"
    })
    int insert(UserBalance record);

    @InsertProvider(type=UserBalanceSqlProvider.class, method="insertSelective")
    int insertSelective(UserBalance record);

    @Select({
        "select",
        "intId, intUserId, douBalance, dateModifiedTime",
        "from l4_user_balance",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserBalance selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=UserBalanceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserBalance record);

    @Update({
        "update l4_user_balance",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "douBalance = #{doubalance,jdbcType=DECIMAL},",
          "dateModifiedTime = #{datemodifiedtime,jdbcType=DATE}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserBalance record);
}