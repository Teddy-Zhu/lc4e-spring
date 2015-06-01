package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysComment;

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

public interface SysCommentMapper {
    @Delete({
        "delete from l4_sys_comment",
        "where intCommentId = #{intcommentid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intcommentid);

    @Insert({
        "insert into l4_sys_comment (intCommentId, intTopicId, ",
        "intCommentTitle, intCommentBody, ",
        "intUserId, isHide, ",
        "isDeleted, dateCreateTime, ",
        "dateModifiedTime)",
        "values (#{intcommentid,jdbcType=INTEGER}, #{inttopicid,jdbcType=VARCHAR}, ",
        "#{intcommenttitle,jdbcType=VARCHAR}, #{intcommentbody,jdbcType=VARCHAR}, ",
        "#{intuserid,jdbcType=INTEGER}, #{ishide,jdbcType=INTEGER}, ",
        "#{isdeleted,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP}, ",
        "#{datemodifiedtime,jdbcType=TIMESTAMP})"
    })
    int insert(SysComment record);

    @InsertProvider(type=SysCommentSqlProvider.class, method="insertSelective")
    int insertSelective(SysComment record);

    @Select({
        "select",
        "intCommentId, intTopicId, intCommentTitle, intCommentBody, intUserId, isHide, ",
        "isDeleted, dateCreateTime, dateModifiedTime",
        "from l4_sys_comment",
        "where intCommentId = #{intcommentid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysComment selectByPrimaryKey(Integer intcommentid);

    @UpdateProvider(type=SysCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysComment record);

    @Update({
        "update l4_sys_comment",
        "set intTopicId = #{inttopicid,jdbcType=VARCHAR},",
          "intCommentTitle = #{intcommenttitle,jdbcType=VARCHAR},",
          "intCommentBody = #{intcommentbody,jdbcType=VARCHAR},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "isHide = #{ishide,jdbcType=INTEGER},",
          "isDeleted = #{isdeleted,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP},",
          "dateModifiedTime = #{datemodifiedtime,jdbcType=TIMESTAMP}",
        "where intCommentId = #{intcommentid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysComment record);
}