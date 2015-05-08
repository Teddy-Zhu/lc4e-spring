package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysTopic;

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

public interface SysTopicMapper {
    @Delete({
        "delete from l4_sys_topic",
        "where intTopicId = #{inttopicid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer inttopicid);

    @Insert({
        "insert into l4_sys_topic (intTopicId, intAreaId, ",
        "intUserId, strTopicTitle, ",
        "strTopicBody, isHide, ",
        "isDeleted, dateCreateTime, ",
        "dateModified)",
        "values (#{inttopicid,jdbcType=INTEGER}, #{intareaid,jdbcType=INTEGER}, ",
        "#{intuserid,jdbcType=INTEGER}, #{strtopictitle,jdbcType=VARCHAR}, ",
        "#{strtopicbody,jdbcType=VARCHAR}, #{ishide,jdbcType=INTEGER}, ",
        "#{isdeleted,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP}, ",
        "#{datemodified,jdbcType=TIMESTAMP})"
    })
    int insert(SysTopic record);

    @InsertProvider(type=SysTopicSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopic record);

    @Select({
        "select",
        "intTopicId, intAreaId, intUserId, strTopicTitle, strTopicBody, isHide, isDeleted, ",
        "dateCreateTime, dateModified",
        "from l4_sys_topic",
        "where intTopicId = #{inttopicid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopic selectByPrimaryKey(Integer inttopicid);

    @UpdateProvider(type=SysTopicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopic record);

    @Update({
        "update l4_sys_topic",
        "set intAreaId = #{intareaid,jdbcType=INTEGER},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "strTopicTitle = #{strtopictitle,jdbcType=VARCHAR},",
          "strTopicBody = #{strtopicbody,jdbcType=VARCHAR},",
          "isHide = #{ishide,jdbcType=INTEGER},",
          "isDeleted = #{isdeleted,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP},",
          "dateModified = #{datemodified,jdbcType=TIMESTAMP}",
        "where intTopicId = #{inttopicid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopic record);
}