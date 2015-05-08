package com.jcos.lc4e.core.database.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.jcos.lc4e.core.database.model.User;

public interface UserMapper {
	@Delete({ "delete from l4_user", "where intUserId = #{intuserid,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer intuserid);

	@Insert({ "insert into l4_user (intUserId, strUserName, ", "strUserMail, strUserNick, ", "strUserPass, strUserPassSalt, ", "intLocked)", "values (#{intuserid,jdbcType=INTEGER}, #{strusername,jdbcType=VARCHAR}, ", "#{strusermail,jdbcType=VARCHAR}, #{strusernick,jdbcType=VARCHAR}, ",
			"#{struserpass,jdbcType=VARCHAR}, #{struserpasssalt,jdbcType=VARCHAR}, ", "#{intlocked,jdbcType=INTEGER})" })
	int insert(User record);

	@InsertProvider(type = UserSqlProvider.class, method = "insertSelective")
	int insertSelective(User record);

	@Select({ "select", "intUserId, strUserName, strUserMail, strUserNick, strUserPass, strUserPassSalt, ", "intLocked", "from l4_user", "where intUserId = #{intuserid,jdbcType=INTEGER}" })
	@ResultMap("BaseResultMap")
	User selectByPrimaryKey(Integer intuserid);

	@UpdateProvider(type = UserSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(User record);

	@Update({ "update l4_user", "set strUserName = #{strusername,jdbcType=VARCHAR},", "strUserMail = #{strusermail,jdbcType=VARCHAR},", "strUserNick = #{strusernick,jdbcType=VARCHAR},", "strUserPass = #{struserpass,jdbcType=VARCHAR},", "strUserPassSalt = #{struserpasssalt,jdbcType=VARCHAR},",
			"intLocked = #{intlocked,jdbcType=INTEGER}", "where intUserId = #{intuserid,jdbcType=INTEGER}" })
	int updateByPrimaryKey(User record);

	@Select({ "select strRoleAbbr from vw_user_role_permission where strUserName = #{username,jdbcType=VARCHAR}" })
	@ResultType(String.class)
	Set<String> findUserRolesByUserName(@Param(value = "username") String username);

	@Select({ "select distinct strPermissionAbbr from vw_user_role_permission where strUserName = #{username,jdbcType=VARCHAR}" })
	@ResultType(String.class)
	Set<String> findUserPermissionsByUserName(@Param(value = "username") String username);

	@Select({ "select", "intUserId, strUserName, strUserMail, strUserNick, strUserPass, strUserPassSalt, ", "intLocked", "from l4_user", "where strUserName = #{username,jdbcType=VARCHAR}" })
	User findUserByUserName(@Param(value = "username") String username);
}