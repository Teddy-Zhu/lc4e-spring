package com.jcos.lc4e.core.database.service;

import java.util.Set;

import com.jcos.lc4e.core.database.model.User;

public interface UserService {

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

	User findByUsername(String username);

	boolean createUser(User user);
	
}
