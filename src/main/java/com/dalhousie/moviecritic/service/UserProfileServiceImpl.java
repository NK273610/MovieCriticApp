package com.dalhousie.moviecritic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.dao.UserDAOImpl;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

	@Autowired
	private UserDAOImpl userDAOImpl;

	public User getUserProfileDetails(String userName) {
		User user = userDAOImpl.getUserProfileByUseName(userName);
		return user;

	}

	public void updateUserProfileDetails(String firstname, String lastname, String imagePath, String username) {
		userDAOImpl.UpdateUserProfileByUserName(firstname, lastname, imagePath, username);
	}

}
