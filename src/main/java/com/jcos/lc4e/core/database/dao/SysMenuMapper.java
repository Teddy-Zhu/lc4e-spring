package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysMenuMapper {
    @Delete({
        "delete from l4_sys_menu",
        "where intMenuId = #{intmenuid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intmenuid);

    @Insert({
        "insert into l4_sys_menu (intMenuId, intParentMenuId, ",
        "intMenuOrderId, strMenuPath, ",
        "strMenuName, strMenuCss, ",
        "strMenuIcon)",
        "values (#{intmenuid,jdbcType=INTEGER}, #{intparentmenuid,jdbcType=INTEGER}, ",
        "#{intmenuorderid,jdbcType=INTEGER}, #{strmenupath,jdbcType=VARCHAR}, ",
        "#{strmenuname,jdbcType=VARCHAR}, #{strmenucss,jdbcType=VARCHAR}, ",
        "#{strmenuicon,jdbcType=VARCHAR})"
    })
    int insert(SysMenu record);

    @InsertProvider(type=SysMenuSqlProvider.class, method="insertSelective")
    int insertSelective(SysMenu record);

    @Select({
        "select",
        "intMenuId, intParentMenuId, intMenuOrderId, strMenuPath, strMenuName, strMenuCss, ",
        "strMenuIcon",
        "from l4_sys_menu",
        "where intMenuId = #{intmenuid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysMenu selectByPrimaryKey(Integer intmenuid);

    @UpdateProvider(type=SysMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysMenu record);

    @Update({
        "update l4_sys_menu",
        "set intParentMenuId = #{intparentmenuid,jdbcType=INTEGER},",
          "intMenuOrderId = #{intmenuorderid,jdbcType=INTEGER},",
          "strMenuPath = #{strmenupath,jdbcType=VARCHAR},",
          "strMenuName = #{strmenuname,jdbcType=VARCHAR},",
          "strMenuCss = #{strmenucss,jdbcType=VARCHAR},",
          "strMenuIcon = #{strmenuicon,jdbcType=VARCHAR}",
        "where intMenuId = #{intmenuid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysMenu record);

    @Select({"select","intMenuId, intParentMenuId, intMenuOrderId, strMenuPath, strMenuName, strMenuCss,strMenuIcon",
        "from l4_sys_menu","ORDER BY intParentMenuId asc,intMenuOrderId asc"
    })
    @ResultMap("BaseResultMap")
    List<SysMenu> selectAllMenus();
}