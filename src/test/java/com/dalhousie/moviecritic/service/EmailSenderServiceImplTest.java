package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.mail.SendFailedException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")
public class EmailSenderServiceImplTest {
	
	@Value("${test.user.username}")
	private String userEmail;
	
	@Value("${test.user.password}")
	private String password;

	@InjectMocks
	EmailSenderServiceImpl emailSenderService;
	
	Authentication authToken;
	
	@Before
	public void setup() {
		authToken = new UsernamePasswordAuthenticationToken(userEmail, password);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loginUser() throws Exception {
		assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	@Test
	public void sendPasswordChangeEmail() throws SendFailedException {
		Mockito.doReturn("success").when(emailSenderService).startSendingEmail(Mockito.anyString(),Mockito.anyString());
		assertEquals("success", emailSenderService.startSendingEmail(userEmail, password));
	}
	
	@Test(expected = SendFailedException.class)
	public void sendPasswordChangeEmailSendFailedExceptionTest() throws SendFailedException{
		Mockito.when(emailSenderService.startSendingEmail("testEmail","testPassword"))
		.thenThrow(new SendFailedException());
		emailSenderService.startSendingEmail("testEmail","testPassword");
	}
	
}
