package com.dalhousie.moviecritic.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.Data.Review;

public interface IMovieService {


	
	public List<Movie> getUpcomingMovies() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException;
    public List<Movie> getPopularMovies() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException;
    public List<Movie> getMovieDetails(String id) throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException;
    public List<Movie> getsearchedmovies(String searchmoviename) throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException;

}
