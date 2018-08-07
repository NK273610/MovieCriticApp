package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.service.ReviewServiceImpl;
import com.dalhousie.moviecritic.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewControllerTest {

	@Mock
	ReviewServiceImpl service=new ReviewServiceImpl();

	@Mock
	UserServiceImpl userService=new UserServiceImpl();

	@InjectMocks
	ReviewController controller;

	MockData data;

	@Mock
	SecurityContextHolder securityHolder;


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getWordsTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		String id = "0";
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getUniqueWords(id);
		assertEquals(new HashMap<String, Float>(), controller.getWords(id));
	}

	@Test
	public void getRating()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		String id = "0";
		Mockito.doReturn(1.0f).when(service).getRating(id);
		assertEquals(1.0f, controller.getRating(id), 0.01);
	}

	@Test
	public void getAgeGroupBar()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		String id = "0";
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getAgeGroupRating(id);
		assertEquals(new HashMap<String, Float>(), controller.getAgeGroupRating(id));
	}

	@Test
	public void getAgeGrp()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		String id = "0";
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getAgeGroup(id);
		assertEquals(new HashMap<String, Float>(), controller.getAgeGrp(id));
	}

	@Test
	public void fetchReviewsTest() throws Exception {
		data=new MockData();
		Mockito.doReturn(data.getReviewData()).when(service).getMovieData(Mockito.any());
		assertTrue(controller.fetchReviews(Mockito.any()).size()==2);
	}

	@Test
	public void fetchUserReviewsTest() throws Exception {
		data=new MockData();
		Mockito.doReturn(data.getReviewData()).when(service).getUserReviews(Mockito.any());
		assertTrue(controller.fetchUserReviews(Mockito.any()).size()==2);
	}

	@Test
	public void fetchReviewsFalseTest() throws Exception {
		data=new MockData();
		Mockito.doReturn(data.getReviewData()).when(service).getMovieData(Mockito.any());
		assertFalse(controller.fetchReviews(Mockito.any()).size()==1);
	}

	@Test
	public void fetchUserReviewsFalseTest() throws Exception {
		data=new MockData();
		Mockito.doReturn(data.getReviewData()).when(service).getUserReviews(Mockito.any());
		assertFalse(controller.fetchUserReviews(Mockito.any()).size()==1);
	}

	@Test
	public void addReviewTest1() throws SQLException {
		User user = Mockito.mock(User.class);
		Mockito.doReturn(user).when(userService).getUsername(Mockito.any());
		//controller.addReview(movieid, moviereview, rating, likability, agegroup)



//	@Test
//	public void addReviewTest1() throws SQLException {
//		Review review = new Review();
//		//Mockito.when(userRegisterService.getUsername(user)).thenReturn(user);
//		Mockito.when(service.validateReview("movie is good")).thenReturn(true);
//		controller.addReview("104","movie is good","5","1", "21-30");
//
//
//	}


	}


}