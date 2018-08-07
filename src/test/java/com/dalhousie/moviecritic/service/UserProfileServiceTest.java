package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.dao.UserDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileServiceTest {
	
	@Value("${test.user.username}")
	private String userEmail;
	
	@Value("${test.user.password}")
	private String password;

	Authentication authToken;
	
	@Mock
	UserDAOImpl userDAOImpl = new UserDAOImpl();

	@InjectMocks
	private IUserProfileService profileService = new UserProfileServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		authToken = new UsernamePasswordAuthenticationToken(userEmail, password);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	@Test
	public void loginUser() throws Exception {
		assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	public void getUserProfileDetails(){
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = profileService.getUserProfileDetails(userName);
		assertNotNull(user);
	}
}
