package com.dalhousie.moviecritic.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.dalhousie.moviecritic.Data.User;

public interface IUserService {
	
	public boolean validateUser(String mail, String password) throws NoSuchAlgorithmException, SQLException;
    public Boolean registerUser(User user);    
    public Boolean isExistingUser(String emailId);
	public void updatePassword(String emailId, String newPassword, String salt);	
	public User getUsername(User user) throws SQLException;
    public User getUserDetails(String user_name);
    public Boolean isUserUnavailable(String username);
    public int changePassword(User user, String newPassword) throws NoSuchAlgorithmException, SQLException;

}
