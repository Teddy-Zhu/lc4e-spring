package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysTopicCollected;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysTopicCollectedMapper {
    @Delete({
        "delete from l4_sys_topic_collected",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_topic_collected (intId, intUserId, ",
        "intCollectedTopicId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{intcollectedtopicid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=DATE})"
    })
    int insert(SysTopicCollected record);

    @InsertProvider(type=SysTopicCollectedSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicCollected record);

    @Select({
        "select",
        "intId, intUserId, intCollectedTopicId, dateCreateTime",
        "from l4_sys_topic_collected",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicCollected selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysTopicCollectedSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicCollected record);

    @Update({
        "update l4_sys_topic_collected",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "intCollectedTopicId = #{intcollectedtopicid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=DATE}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicCollected record);
}