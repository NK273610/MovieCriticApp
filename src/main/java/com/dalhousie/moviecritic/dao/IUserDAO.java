package com.dalhousie.moviecritic.dao;

import java.sql.SQLException;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.Data.User;

public interface IUserDAO {
    public boolean registerUser(User user);
    public User getUsername(User user) throws SQLException;
    public boolean getRegisteredUserByUsername(String username);
    public PasswordSalt validateUser(String mail) throws SQLException;
    public User getUserDetails(String user_name);
    public boolean getRegisteredUserByEmail(String email);
    public void updatePasswordByEmailId(String emailId, String newPassword, String salt);
    public int changePassword(User user) throws SQLException;	

}
