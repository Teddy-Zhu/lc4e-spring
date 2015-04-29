package com.jcos.lc4e.core.service.Impl;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jcos.lc4e.core.database.dao.UserMapper;
import com.jcos.lc4e.core.database.model.User;
import com.jcos.lc4e.core.service.UserService;

@Service
public class UserImpl implements UserService {
	@Inject
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