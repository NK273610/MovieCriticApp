package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.dao.ReviewDAOImple;
import com.dalhousie.moviecritic.utils.BadLanguage;
import com.dalhousie.moviecritic.utils.GraphDataMovie;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewPageImplTest {

	@Spy
	ReviewDAOImple dao;

	MockData data;

	@Spy
	GraphDataMovie moviedata;

	@Mock
	ReviewServiceImpl service;

	@InjectMocks
	ReviewServiceImpl service2;

	@Spy
	ChartDataFactory factory;

	@Mock
	BadLanguage badLanguage = new BadLanguage();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRecommendedMovieTest() {
		Mockito.doReturn(new ArrayList<String>()).when(service).getMovieRecommendForUser(Mockito.anyString());
		assertEquals(new ArrayList<String>(), service.getMovieRecommendForUser("1"));
	}

	@Test
	public void getRecommendedMovieTest2() {
		data = new MockData();
		Mockito.doReturn(data.getFullList()).when(dao).getRatingForMovies();
		Mockito.doReturn(data.getMovie()).when(moviedata).getMovie(Mockito.any(), Mockito.any());
		ArrayList<String> movielist = (ArrayList<String>) service2.getMovieRecommendForUser("1");
		List<String> expected = Arrays.asList("movie3", "movie4", "movie1");
		assertEquals(expected, service2.getMovieRecommendForUser("1"));
	}

	@Test
	public void getUniqueWordTest() {
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getUniqueWords(Mockito.anyString());
		assertEquals(new HashMap<String, Float>(), service.getUniqueWords("1"));
	}

	@Test
	public void getUniqueWordTest2() {
		data = new MockData();
		WordData wr = Mockito.mock(WordData.class);
		Mockito.doReturn(data.getReviewData()).when(dao).getRiviewsForMovie(Mockito.anyString());
		Mockito.doReturn(wr).when(factory).getData(Mockito.any());
		Mockito.doReturn(new HashMap<String, Float>()).when(wr).getDataVisualization(Mockito.any());
		assertEquals(new HashMap<String, Float>(), service2.getUniqueWords("1"));
	}

	@Test
	public void getAgeGroupRatingTest() {
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getAgeGroupRating(Mockito.anyString());
		assertEquals(new HashMap<String, Float>(), service.getUniqueWords("1"));
	}

	@Test
	public void getAgeGroupRatingTest2() {
		data = new MockData();
		BarData br = Mockito.mock(BarData.class);
		Mockito.doReturn(data.getReviewData()).when(dao).getRiviewsForMovie(Mockito.anyString());
		Mockito.doReturn(br).when(factory).getData(Mockito.any());
		Mockito.doReturn(new HashMap<String, Float>()).when(br).getDataVisualization(Mockito.any());
		assertEquals(new HashMap<String, Float>(), service2.getAgeGroupRating("1"));
	}

	@Test
	public void getAgeGroupTest() {
		Mockito.doReturn(new HashMap<String, Float>()).when(service).getAgeGroup(Mockito.anyString());
		assertEquals(new HashMap<String, Float>(), service.getAgeGroup("1"));
	}

	@Test
	public void getAgeGroupTest2() {
		data = new MockData();
		PieData pr = Mockito.mock(PieData.class);
		Mockito.doReturn(data.getReviewData()).when(dao).getRiviewsForMovie(Mockito.anyString());
		Mockito.doReturn(pr).when(factory).getData(Mockito.any());
		Mockito.doReturn(new HashMap<String, Float>()).when(pr).getDataVisualization(Mockito.any());
		assertEquals(new HashMap<String, Float>(), service2.getAgeGroup("1"));
	}

	@Test
	public void getRatingTest() {
		Mockito.doReturn(1.0f).when(service).getRating(Mockito.anyString());
		assertEquals(1.0f, service.getRating("1"), 0.001);
	}

	@Test
	public void getRatingTest2() {
		data = new MockData();
		Mockito.doReturn(data.getReviewData()).when(dao).getRiviewsForMovie(Mockito.anyString());
		Mockito.doReturn(1.0f).when(moviedata).CalculateRating(Mockito.any());
		assertEquals(1.0f, service2.getRating("1"), 0.001);
	}

	@Test
	public void validateReviewTest() throws IOException {

		Mockito.when(badLanguage.badLanguageWords("good")).thenReturn(false);
		Boolean flag = service.validateReview("good");
		assertFalse(flag);
	}

	@Test
	public void validateReviewTest1() throws IOException {

		Mockito.when(badLanguage.badLanguageWords("good1")).thenReturn(true);
		Boolean flag = service.validateReview("good");
		assertFalse(flag);
	}

	@Test
	public void addReviewTest() throws IOException {
		Review review = new Review();
		review.setMovie_id("123");
		service.addReview(review);
	}
}
