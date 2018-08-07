package com.dalhousie.moviecritic.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomAuthenticationProviderTest {

	@InjectMocks
	CustomAuthenticationProvider provider;
	
	@Spy
	UserServiceImpl userService;
	
	@Test
	public void authenticationTest() throws NoSuchAlgorithmException, SQLException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("ankit", "123");
		Mockito.doReturn(true).when(userService).validateUser(Mockito.anyString(), Mockito.anyString());
		provider.authenticate(token);
	}
	
	@Test(expected=AuthenticationServiceException.class)
	public void authenticationSQLExceptionTest() throws NoSuchAlgorithmException, SQLException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("ankit", "123");
		Mockito.doThrow(new SQLException()).when(userService).validateUser(Mockito.anyString(), Mockito.anyString());
		provider.authenticate(token);
	}
	
	@Test(expected=AuthenticationServiceException.class)
	public void authenticationNoSuchAlgorithmExceptionTest() throws NoSuchAlgorithmException, SQLException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("ankit", "123");
		Mockito.doThrow(new NoSuchAlgorithmException()).when(userService).validateUser(Mockito.anyString(), Mockito.anyString());
		provider.authenticate(token);
	}
	
	@Test(expected=AuthenticationServiceException.class)
	public void authenticationIllegalArgumentExceptionTest() throws NoSuchAlgorithmException, SQLException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("ankit", "123");
		Mockito.doThrow(new IllegalArgumentException()).when(userService).validateUser(Mockito.anyString(), Mockito.anyString());
		provider.authenticate(token);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void authenticationBadCredentialsExceptionTest() throws NoSuchAlgorithmException, SQLException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("ankit", "123");
		Mockito.doReturn(false).when(userService).validateUser(Mockito.anyString(), Mockito.anyString());
		provider.authenticate(token);
	}
	
	@Test
	public void supportsTest() throws NoSuchAlgorithmException, SQLException {
		provider.supports(UsernamePasswordAuthenticationToken.class);
	}
}
