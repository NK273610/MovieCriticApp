package com.dalhousie.moviecritic.utils;

import java.util.HashMap;
import java.util.List;

public interface IMovieRecommend {

    public float Compute_Scores(HashMap<String,Integer>compare_to,HashMap<String,Integer>compare_from);
    public int getUniqueCount(List<String> list1, List<String> list2);
}
