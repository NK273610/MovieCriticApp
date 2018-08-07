package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.SendFailedException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.service.EmailSenderServiceImpl;
import com.dalhousie.moviecritic.service.IEmailSenderService;
import com.dalhousie.moviecritic.service.IMovieService;
import com.dalhousie.moviecritic.service.IReviewService;
import com.dalhousie.moviecritic.service.IUserService;
import com.dalhousie.moviecritic.service.MovieServiceImpl;
import com.dalhousie.moviecritic.service.PasswordHashing;
import com.dalhousie.moviecritic.service.PasswordHashingService;
import com.dalhousie.moviecritic.service.ReviewServiceImpl;
import com.dalhousie.moviecritic.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@Value("${test.user.username}")
	private String userEmail;
	
	@Value("${test.user.password}")
	private String password;
	
	
	Authentication authToken;

	@Mock
	IReviewService moviePageService = new ReviewServiceImpl();

	@Mock
	IUserService userRegisterService = new UserServiceImpl();
	
	@Mock
	IMovieService movieService = new  MovieServiceImpl();

	@Mock
	IEmailSenderService emailSenderService = new EmailSenderServiceImpl();

	@Mock
	private PasswordHashing pass = new PasswordHashingService();

	@InjectMocks
	private UserController controller = new UserController();


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

	/*@Test(expected = NoSuchAlgorithmException.class)
	public void addUserNoSuchAlgorithmExceptionTest() throws Exception {
		String firstname = "user";
		String lastname = "pass";
		String username = "testuser";
		String password = null;
		Mockito.when(controller.addUser(firstname, lastname, username, password, userEmail))
				.thenThrow(new NoSuchAlgorithmException());
	}*/

	@Test
	public void forgotPassword() throws NoSuchAlgorithmException, SendFailedException {
		ResponseEntity<String> str = (ResponseEntity<String>) controller.forgotPassword(userEmail);
		assertTrue(str.getBody().startsWith("Not a valid user email"));
	}



	@Test
	public void getUsername() throws SQLException {
		User databaseValUser = controller.getUsername();
		assertNull(databaseValUser);

	}

//	@Test
//	public void addUserTest() throws NoSuchAlgorithmException {
//		String firstname = "user";
//		String lastname = "pass";
//		String username = "testuser";
//		String password = "password123";
//		String email="abc@abc.com";
//		String hashPwd="'$2a$10$w5zqBzMpThvLxOD2wpLEuOnHvwlywUrZquVhGpAJqYYn6MNyfYAEa'";
//		String salt = "'$2a$10$w5zqBzMpThvLxOD2wpLEuO'";
//
//		User user = new User();
//		user.setFirstname("user");
//		user.setLastname("pass");
//		user.setUsername("testuser");
//		user.setUserpass("password123");
//		user.setUseremail("abc@abc.com");
//		user.setSalt("$2a$10$w5zqBzMpThvLxOD2wpLEuO");
//		user.
//
//		PasswordSalt hashPassword = new PasswordSalt(hashPwd,salt);
//		Mockito.when(pass.encryptPassword(password)).thenReturn(hashPassword);
//		User user= new User(username,hashPwd,firstname,lastname,email, salt,null);
//		Mockito.when(userRegisterService.registerUser(user)).thenReturn(false);
//		controller.addUser(user);
//
//	}

	@Test
	public void fetchUserReviewsTest() throws Exception {
		User user = new User();
		user.setFirstname("rahul");
		Mockito.when(userRegisterService.getUserDetails("demo")).thenReturn(user);

		User user1=controller.fetchUserReviews("demo");

		assertTrue(user1.getFirstname().equalsIgnoreCase("rahul"));
	}
	
	@Test
	public void fetchRecommendedMovieTest() throws Exception {
		List<String> recommendedMovieIDList = new ArrayList<>();
		recommendedMovieIDList.add("1");
		
		List<Movie> movieList = new ArrayList<>();
		movieList.add(new Movie());
		String value = "Ankit";
		User user = Mockito.mock(User.class);
		Mockito.doReturn(user).when(userRegisterService).getUsername(Mockito.any());
		Mockito.doReturn(recommendedMovieIDList).when(moviePageService).getMovieRecommendForUser(Mockito.anyString());
		Mockito.doReturn(movieList).when(movieService).getMovieDetails(Mockito.anyString());
		assertEquals(movieList, controller.fetchRecommendedMovieForUser());
	}
	
	@Test
	public void changePasswordTest() throws Exception {
		List<String> recommendedMovieIDList = new ArrayList<>();
		recommendedMovieIDList.add("1");
		
		List<Movie> movieList = new ArrayList<>();
		movieList.add(new Movie());
		String value = "Ankit";
		User user = Mockito.mock(User.class);
		Mockito.doReturn(1).when(userRegisterService).changePassword(Mockito.any(), Mockito.anyString());
		assertEquals(1, controller.changePassword("12346"));
	}

}
