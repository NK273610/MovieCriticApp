package com.dalhousie.moviecritic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomAuthenticationHandlerTest {
	
	@InjectMocks
	CustomAuthenticationHandler handler;

	@Test
	public void registrationconfigBadCredentialsExceptionTest() throws SQLException, IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		AuthenticationException exception = new BadCredentialsException("test");
		handler.onAuthenticationFailure(request, response, exception);
	}
	
	@Test
	public void registrationconfigTest() throws SQLException, IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		AuthenticationException exception = Mockito.mock(AuthenticationException.class);
		handler.onAuthenticationFailure(request, response, exception);
	}
	
}
