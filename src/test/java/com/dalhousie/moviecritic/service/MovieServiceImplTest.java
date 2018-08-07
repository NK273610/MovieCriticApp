package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.dao.BusinessLogicDAOImpl;
import com.dalhousie.moviecritic.dao.IBusinessLogicDAO;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceImplTest {

	@Spy
	IBusinessLogicDAO dao = new BusinessLogicDAOImpl();

	@InjectMocks
	MovieServiceImpl service = new MovieServiceImpl();

	@Mock
	MovieServiceImpl impl = new MovieServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUpcomingMoviesTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Mockito.doReturn(new ArrayList<Movie>()).when(impl).fetchMovieFromAPI(Mockito.anyString());
		assertNotNull(service.getUpcomingMovies());
	}

	@Test
	public void getPopularMoviesTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Mockito.doReturn(new ArrayList<Movie>()).when(impl).fetchMovieFromAPI(Mockito.anyString());
		assertNotNull(service.getPopularMovies());
	}

	@Test
	public void getMovieDetailsTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Mockito.doReturn(new ArrayList<Movie>()).when(impl).fetchMovieFromAPI(Mockito.anyString());
		assertNotNull(service.getMovieDetails("35028"));
	}

	@Test
	public void fetchMovieFromAPIfConditionTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Movie m = new Movie();
		m.setId("353081");
		m.setOverview(
				"When an IMF mission ends badly, the world is faced with dire consequences. As Ethan Hunt takes it upon himself to fulfil his original briefing, the CIA begin to question his loyalty and his motives. The IMF team find themselves in a race against time, hunted by assassins while trying to prevent a global catastrophe.");
		m.setReleaseDate(null);
		m.setTitle("Mission: Impossible - Fallout");
		List<Movie> list = new ArrayList<>();
		list.add(m);
		MovieServiceImpl impl = new MovieServiceImpl();
		assertNotNull(impl.fetchMovieFromAPI(
				"https://api.themoviedb.org/3/movie/popular?api_key=791ba90157746ad2a5d4342a49ce649f&language=en-US&page=1")
				.get(0).getTitle());
	}

	@Test
	public void fetchMovieFromAPIElseConditionTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Movie m = new Movie();
		m.setId("35028");
		m.setOverview(
				"The PhanDom Menace presents the definitive look at the most devoted fans on the planet. Follow the lives of Australia's most passionate Star Wars fans as the moment they've waited 16 years for is suddenly upon them. See the amazing costumes, trivia soaked brains, vast collections and unconditional dedication that make these fans a breed apart. Be stunned by the shockwaves that echo through the Star Wars fan community at the dawn of its new prequel era.");
		m.setReleaseDate(null);
		m.setTitle("The PhanDom Menace");
		List<Movie> list = new ArrayList<>();
		list.add(m);
		MovieServiceImpl impl = new MovieServiceImpl();
		assertEquals(list.get(0).getTitle(), impl.fetchMovieFromAPI(
				"https://api.themoviedb.org/3/movie/35028?api_key=791ba90157746ad2a5d4342a49ce649f&language=en-US")
				.get(0).getTitle());
	}

	@Test
	public void getsearchedmoviesTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		Movie m = new Movie();
		m.setId("35028");
		m.setOverview(
				"The PhanDom Menace presents the definitive look at the most devoted fans on the planet. Follow the lives of Australia's most passionate Star Wars fans as the moment they've waited 16 years for is suddenly upon them. See the amazing costumes, trivia soaked brains, vast collections and unconditional dedication that make these fans a breed apart. Be stunned by the shockwaves that echo through the Star Wars fan community at the dawn of its new prequel era.");
		m.setReleaseDate(null);
		m.setTitle("Ant-Man");
		List<Movie> list = new ArrayList<>();
		list.add(m);
		MovieServiceImpl impl = new MovieServiceImpl();

		assertEquals(list.get(0).getTitle(), impl.getsearchedmovies("Ant Man").get(0).getTitle());
	}

}
