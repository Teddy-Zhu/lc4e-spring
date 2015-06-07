package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysTopicView;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysTopicViewMapper {
    @Delete({
        "delete from l4_sys_topic_view",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_sys_topic_view (intId, intTopicId, ",
        "intViewCount)",
        "values (#{intid,jdbcType=INTEGER}, #{inttopicid,jdbcType=INTEGER}, ",
        "#{intviewcount,jdbcType=INTEGER})"
    })
    int insert(SysTopicView record);

    @InsertProvider(type=SysTopicViewSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicView record);

    @Select({
        "select",
        "intId, intTopicId, intViewCount",
        "from l4_sys_topic_view",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicView selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=SysTopicViewSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicView record);

    @Update({
        "update l4_sys_topic_view",
        "set intTopicId = #{inttopicid,jdbcType=INTEGER},",
          "intViewCount = #{intviewcount,jdbcType=INTEGER}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicView record);
}