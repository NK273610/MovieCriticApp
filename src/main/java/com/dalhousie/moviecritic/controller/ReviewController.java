package com.dalhousie.moviecritic.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.service.IReviewService;
import com.dalhousie.moviecritic.service.IUserService;

@Controller
public class ReviewController
{

	@Autowired
	IUserService userRegisterService;

	@Autowired
	IReviewService service;

	@RequestMapping(value = "/getWords")
	public @ResponseBody Map<String, Float> getWords(@RequestParam("id") String id)
	{
		Map<String, Float> moviedata = service.getUniqueWords(id);
		return moviedata;
	}

	@RequestMapping(value = "/getRating")
	public @ResponseBody float getRating(@RequestParam("id") String id)
	{
		float rating = service.getRating(id);
		return rating;
	}

	@RequestMapping(value = "/getAgeGroupBar")
	public @ResponseBody HashMap<String, Float> getAgeGroupRating(@RequestParam("id") String id)
	{
		HashMap<String, Float> rating_map = service.getAgeGroupRating(id);
		return rating_map;
	}

	@RequestMapping(value = "/getAgegrp")
	public @ResponseBody Map<String, Float> getAgeGrp(@RequestParam("id") String id)
	{
		Map<String, Float> agegrp = service.getAgeGroup(id);
		return agegrp;
	}

	@RequestMapping(value = "/fetchReviews")
	public @ResponseBody List<Review> fetchReviews (@RequestParam("id") String movie_id)
	{
		List<Review> moviedata = null;
		try
		{
			moviedata = service.getMovieData(movie_id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return moviedata;
	}

	@RequestMapping(value = "/fetchUserReviews")
	public @ResponseBody List<Review> fetchUserReviews (@RequestParam("id") String username)
			throws SQLException, ParserConfigurationException, SAXException, JSONException, IOException
	{
		List<Review> userReviews  = null;
		try
		{
			User user = new User();
			userReviews = service.getUserReviews(username);
			for(Review r: userReviews)
				System.out.println(r);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return userReviews;
	}

	@RequestMapping(value = "/addReviews", method = RequestMethod.POST)
	public @ResponseBody boolean addReview(@RequestBody Review review) throws SQLException
	{
		User user = new User();
		user.setUseremail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		User databaseValUser = userRegisterService.getUsername(user);
		review.setUser_name(databaseValUser.getUsername());
		boolean flag = service.validateReview(review.getReviews());
		if(flag)
		{
			return false;
		}
		else
		{
			service.addReview(review);
			return true;
		}
	}
}