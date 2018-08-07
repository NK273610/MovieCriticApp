package com.dalhousie.moviecritic.utils;

import java.util.HashMap;
import java.util.List;

public interface IMovieData {

    public float CalculateRating(List<Float> rating);
    public HashMap<String,Float> getMovie(HashMap<String,HashMap<String,Integer>> fulllist, String user_id);
    public HashMap<String,Float> CalculateUniqueness(List<String> docs);
    public HashMap<String,Float> getAgeGroupCount(List<String> age_grp);
    public HashMap<String,Float> getAverageAgeGroupRating(List<String> age_group,List<Float> rating);
}
