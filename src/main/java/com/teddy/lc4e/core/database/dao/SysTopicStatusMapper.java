package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysTopicStatus;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysTopicStatusMapper {
    @Delete({
        "delete from l4_sys_topic_status",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_topic_status (intId, intTopicId, ",
        "intStatusId, dateCreateTime)",
        "values (#{intid,jdbcType=INTEGER}, #{inttopicid,jdbcType=INTEGER}, ",
        "#{intstatusid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=DATE})"
    })
    int insert(SysTopicStatus record);

    @InsertProvider(type=SysTopicStatusSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicStatus record);

    @Select({
        "select",
        "intId, intTopicId, intStatusId, dateCreateTime",
        "from l4_sys_topic_status",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicStatus selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysTopicStatusSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicStatus record);

    @Update({
        "update l4_sys_topic_status",
        "set intTopicId = #{inttopicid,jdbcType=INTEGER},",
          "intStatusId = #{intstatusid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=DATE}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicStatus record);
}