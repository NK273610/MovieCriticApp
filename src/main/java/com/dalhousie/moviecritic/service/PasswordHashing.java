package com.dalhousie.moviecritic.service;
import java.security.NoSuchAlgorithmException;

import com.dalhousie.moviecritic.Data.PasswordSalt;

public interface PasswordHashing {
	public PasswordSalt encryptPassword(String password) throws NoSuchAlgorithmException;
	public String encryptPassword(String password, String salt) throws NoSuchAlgorithmException;

}
