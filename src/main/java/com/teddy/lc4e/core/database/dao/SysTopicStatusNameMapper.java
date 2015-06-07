package com.teddy.lc4e.core.database.dao;

import com.teddy.lc4e.core.database.model.SysTopicStatusName;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SysTopicStatusNameMapper {
    @Delete({
        "delete from l4_sys_topic_status_name",
        "where intStatusId = #{intstatusid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intstatusid);

    @Insert({
        "insert into l4_sys_topic_status_name (intStatusId, strStatusAbbr, ",
        "strStatusName, strStatusDesription, ",
        "strStatusIcon)",
        "values (#{intstatusid,jdbcType=INTEGER}, #{strstatusabbr,jdbcType=VARCHAR}, ",
        "#{strstatusname,jdbcType=VARCHAR}, #{strstatusdesription,jdbcType=VARCHAR}, ",
        "#{strstatusicon,jdbcType=VARCHAR})"
    })
    int insert(SysTopicStatusName record);

    @InsertProvider(type=SysTopicStatusNameSqlProvider.class, method="insertSelective")
    int insertSelective(SysTopicStatusName record);

    @Select({
        "select",
        "intStatusId, strStatusAbbr, strStatusName, strStatusDesription, strStatusIcon",
        "from l4_sys_topic_status_name",
        "where intStatusId = #{intstatusid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysTopicStatusName selectByPrimaryKey(Integer intstatusid);

    @UpdateProvider(type=SysTopicStatusNameSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysTopicStatusName record);

    @Update({
        "update l4_sys_topic_status_name",
        "set strStatusAbbr = #{strstatusabbr,jdbcType=VARCHAR},",
          "strStatusName = #{strstatusname,jdbcType=VARCHAR},",
          "strStatusDesription = #{strstatusdesription,jdbcType=VARCHAR},",
          "strStatusIcon = #{strstatusicon,jdbcType=VARCHAR}",
        "where intStatusId = #{intstatusid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysTopicStatusName record);
}