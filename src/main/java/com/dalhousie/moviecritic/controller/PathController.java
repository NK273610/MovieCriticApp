package com.dalhousie.moviecritic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController
{
	private static Logger logger = LogManager.getLogger(PathController.class);

	@RequestMapping(value = {"/","/index"})
	public String homepage()
	{
		return "index.html";
	}

	@RequestMapping(value = "/login")
	public String login()
	{
		return "login.html";
	}

	@RequestMapping(value = "/register")
	public String register()
	{
		return "register.html";
	}

	@RequestMapping(value = "/addreviewpage")
	public String addReview()
	{
		return "addreviewpage.html";
	}

	@RequestMapping(value = "/searchresultpage")
	public String showSearchresult()
	{
		return "search.html";
	}
	
	@RequestMapping(value = "/getTheatreMovieDetails")
	public String getTheatreMoviDetails()
	{
		return "theatre.html";
	}
	
	@RequestMapping(value = "/moviepage")
	public String movie()
	{
		return "moviepage.html";
	}
}