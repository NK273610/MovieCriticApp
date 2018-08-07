package com.dalhousie.moviecritic.dao;

import java.util.HashMap;
import java.util.List;

import com.dalhousie.moviecritic.Data.Review;

public interface IReviewDao {

	public void addReview(Review review);
    public List<Review> getRiviewsForMovie(String movieid);
    public HashMap<String,HashMap<String,Integer>> getRatingForMovies();
    public List<Review> getUserReviews(String user_name);
    
}
