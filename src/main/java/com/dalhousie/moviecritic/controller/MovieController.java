package com.dalhousie.moviecritic.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.service.IMovieService;

@Controller
public class MovieController
{
	@Autowired
	IMovieService service;

	@RequestMapping(value = "/getmoviedetails")
	public @ResponseBody List<Movie> getMovieDetails(@RequestParam("id") String id)
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException
	{
		return service.getMovieDetails(id);
	}

	@RequestMapping(value = "/getupcomingmovies")
	public @ResponseBody List<Movie> getUpcomingMovies() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException
	{
		return service.getUpcomingMovies();
	}

	@RequestMapping(value = "/getpopularmovies")
	public @ResponseBody List<Movie> getPopularMovies()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException
	{
		return service.getPopularMovies();
	}
	
	@RequestMapping(value = "/getsearchedmovies")
	public @ResponseBody List<Movie> getsearchedmovies(@RequestParam("searchmoviename") String searchmoviename)
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException
	{
		return service.getsearchedmovies(searchmoviename);
	}
}