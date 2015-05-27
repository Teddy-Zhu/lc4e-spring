package com.jcos.lc4e.core.database.dao;

import com.jcos.lc4e.core.database.model.UserBasicInfo;

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

public interface UserBasicInfoMapper {
    @Delete({
        "delete from l4_user_basicinfo",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer intid);

    @Insert({
        "insert into l4_user_basicinfo (intId, intUserId, ",
        "strPhoneNumber, strSign, ",
        "strAvatar, intLocationId, ",
        "dateBirthDay, dateCreate, ",
        "dateModified)",
        "values (#{intid,jdbcType=INTEGER}, #{intuserid,jdbcType=INTEGER}, ",
        "#{strphonenumber,jdbcType=VARCHAR}, #{strsign,jdbcType=VARCHAR}, ",
        "#{stravatar,jdbcType=VARCHAR}, #{intlocationid,jdbcType=INTEGER}, ",
        "#{datebirthday,jdbcType=TIMESTAMP}, #{datecreate,jdbcType=TIMESTAMP}, ",
        "#{datemodified,jdbcType=TIMESTAMP})"
    })
    int insert(UserBasicInfo record);

    @InsertProvider(type=UserBasicInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UserBasicInfo record);

    @Select({
        "select",
        "intId, intUserId, strPhoneNumber, strSign, strAvatar, intLocationId, dateBirthDay, ",
        "dateCreate, dateModified",
        "from l4_user_basicinfo",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserBasicInfo selectByPrimaryKey(Integer intid);

    @UpdateProvider(type=UserBasicInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserBasicInfo record);

    @Update({
        "update l4_user_basicinfo",
        "set intUserId = #{intuserid,jdbcType=INTEGER},",
          "strPhoneNumber = #{strphonenumber,jdbcType=VARCHAR},",
          "strSign = #{strsign,jdbcType=VARCHAR},",
          "strAvatar = #{stravatar,jdbcType=VARCHAR},",
          "intLocationId = #{intlocationid,jdbcType=INTEGER},",
          "dateBirthDay = #{datebirthday,jdbcType=TIMESTAMP},",
          "dateCreate = #{datecreate,jdbcType=TIMESTAMP},",
          "dateModified = #{datemodified,jdbcType=TIMESTAMP}",
        "where intId = #{intid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserBasicInfo record);
}