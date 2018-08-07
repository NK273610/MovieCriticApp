package com.dalhousie.moviecritic.service;

import com.dalhousie.moviecritic.Data.User;

public interface IUserProfileService {

	public User getUserProfileDetails(String userName);

	public void updateUserProfileDetails(String firstname, String lastname, String imagePath, String username);
}
