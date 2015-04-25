package com.jcos.lc4e.core.database.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

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
}