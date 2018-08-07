package com.dalhousie.moviecritic.controller;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.service.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.SendFailedException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

@Controller
public class UserController {
	@Value("${default.image.path}")
	private String imagePath;

	@Autowired
	IUserService userService;

	@Autowired
	IReviewService moviePageService;

	@Autowired
	IMovieService movieService;

	@Autowired
	IEmailSenderService emailSenderService;

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIabcdefghi0123456789";

	public PasswordHashing pass;

	public void setPass(PasswordHashing pass) {
		this.pass = pass;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody boolean addUser(@RequestBody User user) throws NoSuchAlgorithmException {
		if (pass == null) {
			pass = new PasswordHashingService();
		}
		PasswordHashing pass = new PasswordHashingService();

		PasswordSalt hashPassword;
		try {
			hashPassword = pass.encryptPassword(user.getUserpass());
			String salt = hashPassword.getSalt();
			String passwordHash = hashPassword.getHashPassword();
			Boolean existingEmail = userService.isExistingUser(user.getUseremail());
			Boolean unavailableUser = userService.isUserUnavailable(user.getUseremail());

			if (existingEmail || unavailableUser) {
				return false;
			}
			user.setSalt(salt);
			user.setImagePath(imagePath);
			if (userService.registerUser(user)) {
				System.out.println("Registration successful");
			} else {
				System.out.println("no successful");
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		return true;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String emailId) throws SendFailedException {
		String message = "";
		String newPassword = randomAlphaNumeric();
		PasswordHashing pass = new PasswordHashingService();
		PasswordSalt hashPassword;
		try {
			hashPassword = pass.encryptPassword(newPassword);
			String salt = hashPassword.getSalt();
			String passwordHash = hashPassword.getHashPassword();
			if (userService.isExistingUser(emailId)) {
				userService.updatePassword(emailId, passwordHash, salt);
				emailSenderService.sendPasswordChangeEmail(emailId, newPassword);
				message = "New Password has been sent to your email";
			} else {
				message = "Not a valid user email Id : Please try again";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(message);
	}

	@RequestMapping(value = "/getUsername")
	public @ResponseBody User getUsername() throws SQLException {
		User user = new User();
		user.setUseremail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		User databaseValUser = userService.getUsername(user);
		return databaseValUser;
	}

	public String randomAlphaNumeric() {
		int count = 8;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	@RequestMapping(value = "/getUserDetails")
	public @ResponseBody User fetchUserReviews(@RequestParam("uid") String username) {
		User user = new User();

		try {
			user = userService.getUserDetails(username);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@RequestMapping(value = "/getSessionUser", method = RequestMethod.GET)
	public ResponseEntity<?> getSessionUser() {
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(userName);
	}

	@RequestMapping(value = "/fetchRecommendedMovie", method = RequestMethod.GET)
	public @ResponseBody List<Movie> fetchRecommendedMovieForUser()
			throws SQLException, ParserConfigurationException, SAXException, IOException, JSONException {
		User user = new User();
		List<Movie> recommendedList = new ArrayList<>();
		user.setUseremail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		User databaseValUser = userService.getUsername(user);
		List<String> recommendedMovieIDList = moviePageService.getMovieRecommendForUser(databaseValUser.getUsername());
		for (String movieId : recommendedMovieIDList) {
			List<Movie> movieList = movieService.getMovieDetails(movieId);
			recommendedList.add(movieList.get(0));
		}
		return recommendedList;
	}

	@RequestMapping(value = "changePassword")
	public @ResponseBody int changePassword(@RequestParam("password") String newPassword)
			throws NoSuchAlgorithmException, SQLException {
		User user = new User();
		user.setUseremail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		user.setUserpass(newPassword);
		return userService.changePassword(user, newPassword);
	}
}
