package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.PasswordSalt;
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordHashingServiceTest {

	@Value("${test.user.username}")
	private String userEmail;
	
	@Value("${test.user.password}")
	private String password;
	
	
	Authentication authToken;

	PasswordHashingService passwordHashing = new PasswordHashingService();

	@Before
	public void setup() {
		authToken = new UsernamePasswordAuthenticationToken(userEmail, password);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	@Test
	public void loginUser() throws Exception {
		assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@Test(expected = NoSuchAlgorithmException.class)
	public void encryptPasswordNoSuchAlgorithmExceptionTest() throws Exception {
		String password = null;
		Mockito.when(passwordHashing.encryptPassword(password)).thenThrow(new NoSuchAlgorithmException());
	}

	@Test
	public void encryptPassword() throws NoSuchAlgorithmException {
		String password = "testPassword";
		PasswordSalt salt = passwordHashing.encryptPassword(password);
		assertNotNull(salt);
	}
}
