package com.user.service.service;

import java.util.List;

import com.user.service.entity.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser();
	
	User GetUserById(String userId);
	
	User updateUser(String userId, User user);
	
	String deleteUser(String userId);
}
