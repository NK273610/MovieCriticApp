package com.dalhousie.moviecritic.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.dao.IUserDAO;

@Service
@Component
public class UserServiceImpl implements IUserService {

	private static Logger logger = (Logger) LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDAO userDao;

	@Override
	public boolean validateUser(String mail, String password)
			throws NoSuchAlgorithmException, SQLException, IllegalArgumentException {
		PasswordSalt passwordSalt = userDao.validateUser(mail);
		String dbpwd = passwordSalt.getHashPassword();
		String dbsalt = passwordSalt.getSalt();
		PasswordHashing passwordHashing = new PasswordHashingService();

		String hashGenerated = passwordHashing.encryptPassword(password, dbsalt);
		if (hashGenerated.equals(dbpwd)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Boolean registerUser(User user) {
		return userDao.registerUser(user);
	}

	@Override
	public Boolean isExistingUser(String emailId) {
		return userDao.getRegisteredUserByEmail(emailId);
	}

	public Boolean isUserUnavailable(String username) {
		return userDao.getRegisteredUserByUsername(username);
	}

	@Override
	public void updatePassword(String emailId, String newPassword, String salt) {
		userDao.updatePasswordByEmailId(emailId, newPassword, salt);
	}

	@Override
	public User getUsername(User user) throws SQLException {
		return userDao.getUsername(user);
	}

	@Override
	public User getUserDetails(String user_name) {
		return userDao.getUserDetails(user_name);
	}

	@Override
	public int changePassword(User user, String newPassword) throws NoSuchAlgorithmException, SQLException {
		PasswordHashing pass = new PasswordHashingService();
		PasswordSalt hashPassword;
		int result = 0;
		hashPassword = pass.encryptPassword(newPassword);
		String salt = hashPassword.getSalt();
		String passwordHash = hashPassword.getHashPassword();
		user.setUserpass(passwordHash);
		user.setSalt(salt);
		result = userDao.changePassword(user);

		return result;
	}
}