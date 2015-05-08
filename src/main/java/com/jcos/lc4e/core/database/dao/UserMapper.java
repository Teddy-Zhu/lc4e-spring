package com.jcos.lc4e.core.database.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jcos.lc4e.core.database.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer intuserid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer intuserid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	Set<String> findUserRolesByUserName(@Param(value = "username") String username);

	Set<String> findUserPermissionsByUserName(@Param(value = "username") String username);

	User findUserByUserName(@Param(value = "username") String username);
	
	
	@Select("SELECT * FROM l4_user WHERE strUserName = #{username}") 
	User selecttest(@Param(value = "username") String username);
}