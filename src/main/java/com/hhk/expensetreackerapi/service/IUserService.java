package com.hhk.expensetreackerapi.service;

import com.hhk.expensetreackerapi.entity.User;
import com.hhk.expensetreackerapi.entity.UserModel;

public interface IUserService {

	User createUser(UserModel user);

	User readUser();

	User updateUser(UserModel user);

	void deleteUser();
	
	User getLoggedInUser();

}
