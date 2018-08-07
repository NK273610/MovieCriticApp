package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.service.TheatreService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TheatreControllerTest {

	MockData mock;
	
	@Value("${test.user.username}")
	private String userEmail;

	@Value("${test.user.password}")
	private String password;

	Authentication authToken;

	@Mock
	TheatreService theatreService;

	@InjectMocks
	private TheatreController controller;

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

	@Test
	public void getTheatreDetails() {
		mock = new MockData();
		Mockito.doReturn(mock.getTheatreSlotDetails()).when(theatreService).getTheatreSlotDetails();
		assertNotNull(controller.getTheatreDetails());
	}
}
