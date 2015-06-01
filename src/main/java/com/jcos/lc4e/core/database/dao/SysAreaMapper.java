package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysArea;

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

public interface SysAreaMapper {
    @Delete({
        "delete from l4_sys_area",
        "where intAreaId = #{intareaid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intareaid);

    @Insert({
        "insert into l4_sys_area (intAreaId, intParentAreaId, ",
        "strAreaAbbr, strAreaName, ",
        "strAreaDescription, strAreaCss, ",
        "strAreaIcon, isShow, ",
        "intUserId, dateCreateTime)",
        "values (#{intareaid,jdbcType=INTEGER}, #{intparentareaid,jdbcType=INTEGER}, ",
        "#{strareaabbr,jdbcType=VARCHAR}, #{strareaname,jdbcType=VARCHAR}, ",
        "#{strareadescription,jdbcType=VARCHAR}, #{strareacss,jdbcType=VARCHAR}, ",
        "#{strareaicon,jdbcType=VARCHAR}, #{isshow,jdbcType=INTEGER}, ",
        "#{intuserid,jdbcType=INTEGER}, #{datecreatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysArea record);

    @InsertProvider(type=SysAreaSqlProvider.class, method="insertSelective")
    int insertSelective(SysArea record);

    @Select({
        "select",
        "intAreaId, intParentAreaId, strAreaAbbr, strAreaName, strAreaDescription, strAreaCss, ",
        "strAreaIcon, isShow, intUserId, dateCreateTime",
        "from l4_sys_area",
        "where intAreaId = #{intareaid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysArea selectByPrimaryKey(Integer intareaid);

    @UpdateProvider(type=SysAreaSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysArea record);

    @Update({
        "update l4_sys_area",
        "set intParentAreaId = #{intparentareaid,jdbcType=INTEGER},",
          "strAreaAbbr = #{strareaabbr,jdbcType=VARCHAR},",
          "strAreaName = #{strareaname,jdbcType=VARCHAR},",
          "strAreaDescription = #{strareadescription,jdbcType=VARCHAR},",
          "strAreaCss = #{strareacss,jdbcType=VARCHAR},",
          "strAreaIcon = #{strareaicon,jdbcType=VARCHAR},",
          "isShow = #{isshow,jdbcType=INTEGER},",
          "intUserId = #{intuserid,jdbcType=INTEGER},",
          "dateCreateTime = #{datecreatetime,jdbcType=TIMESTAMP}",
        "where intAreaId = #{intareaid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysArea record);
}