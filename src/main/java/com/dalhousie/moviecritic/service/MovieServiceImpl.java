package com.dalhousie.moviecritic.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.dao.IUserDAO;
import com.dalhousie.moviecritic.utils.BaseAPIUrl;
import com.dalhousie.moviecritic.utils.IAPIUrl;
import com.dalhousie.moviecritic.utils.MovieDetailURLDecorator;
import com.dalhousie.moviecritic.utils.PopularMovieURLDecorator;
import com.dalhousie.moviecritic.utils.SearchMovieByNameURLDecorator;
import com.dalhousie.moviecritic.utils.UpcomingMovieURLDecorator;

@Service
@Component
public class MovieServiceImpl implements IMovieService {

	@Autowired
	IUserDAO userDao;

	@Value("${api.imageurl.prefix}")
	private String imageURLPrefix;

	@Override
	public List<Movie> getUpcomingMovies()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		// https://stackoverflow.com/questions/7704827/java-reading-xml-file
		IAPIUrl url = new UpcomingMovieURLDecorator(new BaseAPIUrl());
		List<Movie> movieList = fetchMovieFromAPI(url.getURL());
		return movieList;
	}

	@Override
	public List<Movie> getPopularMovies()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		IAPIUrl url = new PopularMovieURLDecorator(new BaseAPIUrl());
		List<Movie> movieList = fetchMovieFromAPI(url.getURL());
		return movieList;
	}

	@Override
	public List<Movie> getMovieDetails(String id)
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		IAPIUrl url = new MovieDetailURLDecorator(new BaseAPIUrl());
		String searchmovieByIdURL = url.getURL().replace("MOVIE_ID", id);
		List<Movie> movie = fetchMovieFromAPI(searchmovieByIdURL);
		return movie;
	}

	public List<Movie> fetchMovieFromAPI(String url) throws JSONException {
		// https://stackoverflow.com/questions/42365266/call-another-rest-api-from-my-server-in-spring-boot
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = new JSONObject(result);
		Movie movie = null;
		List<Movie> movieList = new ArrayList<Movie>();

		if (jsonObject.has("results")) {
			JSONArray movieResult = jsonObject.getJSONArray("results");
			for (int i = 0; i < movieResult.length(); i++) {
				JSONObject jsonMovie = movieResult.getJSONObject(i);
				movie = new Movie();
				movie.setId(jsonMovie.getString("id"));
				Iterator<String> keys = jsonMovie.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					Pattern namePattern = Pattern.compile("original_*");
					Matcher nameMatcher = namePattern.matcher(key);

					Pattern datePattern = Pattern.compile("\\b_date");
					Matcher dateMatcher = datePattern.matcher(key);

					if (nameMatcher.find() && !key.contains("language")) {
						movie.setTitle(jsonMovie.getString(key));
					}

					if (dateMatcher.find()) {
						movie.setReleaseDate(jsonMovie.getString("\\b_date"));
					}
				}
				movie.setOverview(jsonMovie.getString("overview"));
				movie.setImageURL(imageURLPrefix + jsonMovie.getString("poster_path"));
				movieList.add(movie);
			}
			return movieList;
		} else {
			movie = new Movie();
			movie.setId(jsonObject.getString("id"));
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Pattern namePattern = Pattern.compile("original_*");
				Matcher nameMatcher = namePattern.matcher(key);

				Pattern datePattern = Pattern.compile("\\b_date");
				Matcher dateMatcher = datePattern.matcher(key);

				if (nameMatcher.find() && !key.contains("language")) {
					movie.setTitle(jsonObject.getString(key));
				}

				if (dateMatcher.find()) {
					movie.setReleaseDate(jsonObject.getString("\\b_date"));
				}
			}

			movie.setOverview(jsonObject.getString("overview"));
			movie.setImageURL(imageURLPrefix + jsonObject.getString("poster_path"));
			movieList.add(movie);
			return movieList;
		}

	}

	@Override
	public List<Movie> getsearchedmovies(String searchmoviename)
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		IAPIUrl url = new SearchMovieByNameURLDecorator(new BaseAPIUrl());
		String searchmovieByNameURL = url.getURL().replaceAll("MOVIE_NAME", searchmoviename);
		List<Movie> movie = fetchMovieFromAPI(searchmovieByNameURL);
		return movie;
	}

}
