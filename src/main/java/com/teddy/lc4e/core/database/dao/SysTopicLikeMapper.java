package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysTopicLike;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysTopicLikeMapper {
    @Delete({
        "delete from l4_sys_topic_like",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_topic_like (intId, intTopicId, ",
        "intUserId, dateCreate)",
        "values (#{intid,jdbcType=INTEGER}, #{inttopicid,jdbcType=INTEGER}, ",
        "#{intuserid,jdbcType=INTEGER}, #{datecreate,jdbcType=TIMESTAMP})"
    })
    int insert(SysTopicLike record);

    @InsertProvider(type=SysTopicLikeSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicLike record);

    @Select({
        "select",
        "intId, intTopicId, intUserId, dateCreate",
        "from l4_sys_topic_like",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicLike selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysTopicLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicLike record);

    @Update({
        "update l4_sys_topic_like",
        "set intTopicId = #{inttopicid,jdbcType=INTEGER},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "dateCreate = #{datecreate,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicLike record);
}