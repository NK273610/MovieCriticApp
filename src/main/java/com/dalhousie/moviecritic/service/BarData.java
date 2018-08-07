package com.dalhousie.moviecritic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.utils.IMovieData;

public class BarData implements IChartData {

    IMovieData moviedata;

    BarData(IMovieData movieData)
    {
        this.moviedata=movieData;
    }
    @Override
    public HashMap<String, Float> getDataVisualization(List<Review> reviewList) {
        List<String> age_grp=new ArrayList<>();
        List<Float> age_rate=new ArrayList<>();
        for(Review review:reviewList)
        {
            age_grp.add(review.getAge_group());
            age_rate.add(review.getRating());

        }
        HashMap<String,Float> result= moviedata.getAverageAgeGroupRating(age_grp,age_rate);
        return result;
    }
}
