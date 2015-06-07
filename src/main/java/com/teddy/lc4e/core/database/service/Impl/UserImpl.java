package com.teddy.lc4e.core.database.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy.lc4e.core.database.dao.UserMapper;
import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.database.service.UserService;

@Service
public class UserImpl implements UserService {
	@Autowired
	private UserMapper userDao;

	@Override
	public Set<String> findRoles(String username) {
		return userDao.findUserRolesByUserName(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return userDao.findUserPermissionsByUserName(username);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findUserByUserName(username);
	}

	@Override
	public boolean createUser(User user) {
		Integer i = userDao.insertSelective(user);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

}
