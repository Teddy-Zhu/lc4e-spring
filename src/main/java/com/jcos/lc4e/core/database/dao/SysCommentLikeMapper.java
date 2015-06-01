package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysCommentLike;

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

public interface SysCommentLikeMapper {
    @Delete({
        "delete from l4_sys_comment_like",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_comment_like (intId, intCommentId, ",
        "intUserId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intcommentid,jdbcType=INTEGER}, ",
        "#{intuserid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysCommentLike record);

    @InsertProvider(type=SysCommentLikeSqlProvider.class, method="insertSelective")
    int insertSelective(SysCommentLike record);

    @Select({
        "select",
        "intId, intCommentId, intUserId, dateCreateTime",
        "from l4_sys_comment_like",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysCommentLike selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysCommentLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysCommentLike record);

    @Update({
        "update l4_sys_comment_like",
        "set intCommentId = #{intcommentid,jdbcType=INTEGER},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysCommentLike record);
}