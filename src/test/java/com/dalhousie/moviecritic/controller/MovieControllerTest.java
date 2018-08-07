package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.service.IMovieService;
import com.dalhousie.moviecritic.service.MovieServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieControllerTest {

	@InjectMocks
	MovieController controller = new MovieController();
	
	@Mock
	IMovieService service = new MovieServiceImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getMovieDetailsTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		String id = "0";
		assertEquals(new ArrayList<Movie>(), controller.getMovieDetails(id));
	}
	
	@Test(expected = ParserConfigurationException.class)
	public void getMovieDetailsParserConfigurationExceptionTest() throws Exception {
		String id = "0";

		Mockito.when(controller.getMovieDetails(id)).thenThrow(new ParserConfigurationException());
		controller.getMovieDetails(id);
	}
	
	@Test(expected = SAXException.class)
	public void getMovieDetailsSAXExceptionTest() throws Exception {
		String id = "0";

		Mockito.when(controller.getMovieDetails(id)).thenThrow(new SAXException());
		controller.getMovieDetails(id);
	}
	
	@Test(expected = IOException.class)
	public void getMovieDetailsIOExceptionTest() throws Exception {
		String id = "0";

		Mockito.when(controller.getMovieDetails(id)).thenThrow(new IOException());
		controller.getMovieDetails(id);
	}
	
	@Test(expected = JSONException.class)
	public void getMovieDetailsJSONExceptionTest() throws Exception {
		String id = "0";

		Mockito.when(controller.getMovieDetails(id)).thenThrow(new JSONException("Invalid"));
		controller.getMovieDetails(id);
	}
	
	@Test
	public void getUpcomingMoviesTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		assertEquals(new ArrayList<Movie>(), controller.getUpcomingMovies());
	}

	@Test(expected = ParserConfigurationException.class)
	public void getUpcomingMoviesParserConfigurationExceptionTest() throws Exception {

		Mockito.when(controller.getUpcomingMovies()).thenThrow(new ParserConfigurationException());
		controller.getUpcomingMovies();
	}

	@Test(expected = SAXException.class)
	public void getUpcomingMoviesSAXExceptionTest() throws Exception {

		Mockito.when(controller.getUpcomingMovies()).thenThrow(new SAXException());
		controller.getUpcomingMovies();
	}

	@Test(expected = IOException.class)
	public void getUpcomingMoviesIOExceptionTest() throws Exception {

		Mockito.when(controller.getUpcomingMovies()).thenThrow(new IOException());
		controller.getUpcomingMovies();
	}

	@Test(expected = JSONException.class)
	public void getUpcomingMoviesJSONExceptionTest() throws Exception {

		Mockito.when(controller.getUpcomingMovies()).thenThrow(new JSONException("Invalid"));
		controller.getUpcomingMovies();
	}

	@Test
	public void getPopularMoviesTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		assertEquals(new ArrayList<Movie>(), controller.getPopularMovies());
		// assertEquals(new ArrayList<Movie>(), controller.getMovieDetails(id), 0D);
	}

	@Test(expected = ParserConfigurationException.class)
	public void getPopularMoviesParserConfigurationExceptionTest() throws Exception {

		Mockito.when(controller.getPopularMovies()).thenThrow(new ParserConfigurationException());
		controller.getPopularMovies();
	}

	@Test(expected = SAXException.class)
	public void getPopularMoviesSAXExceptionTest() throws Exception {

		Mockito.when(controller.getPopularMovies()).thenThrow(new SAXException());
		controller.getPopularMovies();
	}

	@Test(expected = IOException.class)
	public void getPopularMoviesIOExceptionTest() throws Exception {

		Mockito.when(controller.getPopularMovies()).thenThrow(new IOException());
		controller.getPopularMovies();
	}

	@Test(expected = JSONException.class)
	public void getPopularMoviesJSONExceptionTest() throws Exception {

		Mockito.when(controller.getPopularMovies()).thenThrow(new JSONException("Invalid"));
		controller.getPopularMovies();
	}

	@Test
	public void getsearchedmoviesTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		assertEquals(new ArrayList<Movie>(), controller.getsearchedmovies("ABC"));
		// assertEquals(new ArrayList<Movie>(), controller.getMovieDetails(id), 0D);
	}

	@Test(expected = ParserConfigurationException.class)
	public void getsearchedmoviesParserConfigurationExceptionTest() throws Exception {

		Mockito.when(controller.getsearchedmovies("ABC")).thenThrow(new ParserConfigurationException());
		controller.getsearchedmovies("ABC");
	}

	@Test(expected = SAXException.class)
	public void getsearchedmoviesSAXExceptionTest() throws Exception {

		Mockito.when(controller.getsearchedmovies("ABC")).thenThrow(new SAXException());
		controller.getsearchedmovies("ABC");
	}

	@Test(expected = IOException.class)
	public void getsearchedmoviesIOExceptionTest() throws Exception {

		Mockito.when(controller.getsearchedmovies("ABC")).thenThrow(new IOException());
		controller.getsearchedmovies("ABC");
	}

	@Test(expected = JSONException.class)
	public void getsearchedmoviesJSONExceptionTest() throws Exception {

		Mockito.when(controller.getsearchedmovies("ABC")).thenThrow(new JSONException("Invalid"));
		controller.getsearchedmovies("ABC");
	}
	
	
	
}
