package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysTopicBlocked;

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

public interface SysTopicBlockedMapper {
    @Delete({
        "delete from l4_sys_topic_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_topic_blocked (intId, intUserId, ",
        "intBlockedTopicId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intblockedtopicid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysTopicBlocked record);

    @InsertProvider(type=SysTopicBlockedSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicBlocked record);

    @Select({
        "select",
        "intId, intUserId, intBlockedTopicId, dateCreateTime",
        "from l4_sys_topic_blocked",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicBlocked selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysTopicBlockedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicBlocked record);

    @Update({
        "update l4_sys_topic_blocked",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intBlockedTopicId = #{intblockedtopicid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicBlocked record);
}