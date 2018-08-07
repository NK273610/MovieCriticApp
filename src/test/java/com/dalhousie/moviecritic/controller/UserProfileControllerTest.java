package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertNotNull;
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
import org.springframework.web.multipart.MultipartFile;

import com.dalhousie.moviecritic.service.IUserProfileService;
import com.dalhousie.moviecritic.service.UserProfileServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileControllerTest {

	@Value("${test.user.username}")
	private String userEmail;
	
	@Value("${test.user.password}")
	private String password;
	
	Authentication authToken;
	
	@Mock
	IUserProfileService userProfileService = new UserProfileServiceImpl();

	@InjectMocks
	private UserProfileController controller = new UserProfileController();

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
	
	/*@Test
	public void getUserProfile(){
		User user = controller.getUserProfile();
		assertNull(user);
	}*/
	
	@Test
	public void updateUserProfile(){
		MultipartFile profileImage = null;
		String firstName = "test";
		String lastName = "test";
		String result = controller.updateUserProfile(profileImage, lastName, firstName);
		assertTrue(result.equals("error"));
	}
}
