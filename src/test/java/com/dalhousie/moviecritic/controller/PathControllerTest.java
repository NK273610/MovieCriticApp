package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.service.IMovieService;
import com.dalhousie.moviecritic.service.IUserService;
import com.dalhousie.moviecritic.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PathControllerTest {

	PathController controller = new PathController();

	IMovieService service ;

	@Mock
	IUserService userRegisterService = new UserServiceImpl();

	//private DemoController controller = new DemoController();

	User user = new User();



	@Test
	public void homepageTest() {
		assertEquals("index.html", controller.homepage());
	}
	
	@Test
	public void loginTest() {
		assertEquals("login.html", controller.login());
	}
	
	@Test
	public void registerTest() {
		assertEquals("register.html", controller.register());
	}
	
	@Test
	public void addReviewTest() {
		assertEquals("addreviewpage.html", controller.addReview());
	}
	
	@Test
	public void showSearchresultTest() {
		assertEquals("search.html", controller.showSearchresult());
	}
	
	@Test
	public void theatreTest() {
		assertEquals("theatre.html", controller.getTheatreMoviDetails());
	}
	
	@Test
	public void movieTest() {
		assertEquals("moviepage.html", controller.movie());
	}

	
	
}
