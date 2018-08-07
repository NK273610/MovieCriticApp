package com.dalhousie.moviecritic.service;
import java.security.NoSuchAlgorithmException;

import com.dalhousie.moviecritic.Data.TheatreFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.dalhousie.moviecritic.Data.PasswordSalt;


public class PasswordHashingService implements PasswordHashing {
	private String salt;
	private String encryptedPassword;

	public PasswordSalt encryptPassword(String password) throws NoSuchAlgorithmException {
		salt = BCrypt.gensalt(10);
		if (password != null && !password.equals("")) {
			encryptedPassword = BCrypt.hashpw(password, salt);
		} else {
			throw new NoSuchAlgorithmException();
		}
		PasswordSalt passwordSalt = TheatreFactory.getPasswordObject(encryptedPassword,salt);
		return passwordSalt;
	}

	public String encryptPassword(String password, String salt) throws NoSuchAlgorithmException, IllegalArgumentException  {
		encryptedPassword = BCrypt.hashpw(password, salt);		
		return encryptedPassword;
	}
}
