package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.SysUserLocations;

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

public interface SysUserLocationsMapper {
    @Delete({
        "delete from l4_sys_user_locations",
        "where intLocationId = #{intlocationid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intlocationid);

    @Insert({
        "insert into l4_sys_user_locations (intLocationId, intProvinceId, ",
        "intCityId, intAreaId)",
        "values (#{intlocationid,jdbcType=INTEGER}, #{intprovinceid,jdbcType=INTEGER}, ",
        "#{intcityid,jdbcType=INTEGER}, #{intareaid,jdbcType=INTEGER})"
    })
    int insert(SysUserLocations record);

    @InsertProvider(type=SysUserLocationsSqlProvider.class, method="insertSelective")
    int insertSelective(SysUserLocations record);

    @Select({
        "select",
        "intLocationId, intProvinceId, intCityId, intAreaId",
        "from l4_sys_user_locations",
        "where intLocationId = #{intlocationid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SysUserLocations selectByPrimaryKey(Integer intlocationid);

    @UpdateProvider(type=SysUserLocationsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserLocations record);

    @Update({
        "update l4_sys_user_locations",
        "set intProvinceId = #{intprovinceid,jdbcType=INTEGER},",
          "intCityId = #{intcityid,jdbcType=INTEGER},",
          "intAreaId = #{intareaid,jdbcType=INTEGER}",
        "where intLocationId = #{intlocationid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUserLocations record);
}