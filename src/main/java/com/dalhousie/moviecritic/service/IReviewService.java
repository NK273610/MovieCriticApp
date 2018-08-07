package com.dalhousie.moviecritic.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Review;

public interface IReviewService {

    public List<Review> getMovieData(String movieid);
    public List<Review> getUserReviews(String user_name) throws SQLException, ParserConfigurationException, SAXException, JSONException, IOException;
    public List<String> getMovieRecommendForUser(String userid);
    public HashMap<String,Float> getUniqueWords(String movieid);
    public float getRating(String movieid);
    public HashMap<String,Float> getAgeGroup(String movieid);
    public HashMap<String,Float> getAgeGroupRating(String movieid);
    public boolean validateReview(String review);
	public void addReview(Review review);
}
