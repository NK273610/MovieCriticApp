package com.dalhousie.moviecritic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.utils.IMovieData;


public class WordData implements IChartData {

    IMovieData moviedata;

    public WordData() {
    }

    WordData(IMovieData movieData)
    {
        this.moviedata=movieData;
    }

    @Override
    public HashMap<String, Float> getDataVisualization(List<Review> reviewlist) {
        List<String> reviews=new ArrayList<>();
        for(Review review:reviewlist)
        {
            reviews.add(review.getReviews());
        }
        HashMap<String,Float> result=moviedata.CalculateUniqueness(reviews);
        return result;
    }
}
