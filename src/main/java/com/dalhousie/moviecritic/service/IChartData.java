package com.dalhousie.moviecritic.service;

import com.dalhousie.moviecritic.Data.Review;

import java.util.HashMap;
import java.util.List;

public interface IChartData {

    public HashMap<String,Float> getDataVisualization(List<Review> reviewlist);
}
