package com.dalhousie.moviecritic.Data;

public class PasswordSalt {
	private String hashPassword;
	private String salt;
	
	public PasswordSalt() {
		
	}
	
	public PasswordSalt(String hashPassword, String salt) {
		this.setHashPassword(hashPassword);
		this.setSalt(salt);
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
