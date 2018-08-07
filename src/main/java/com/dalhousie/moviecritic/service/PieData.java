package com.dalhousie.moviecritic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.utils.IMovieData;

public class PieData implements IChartData {

	IMovieData moviedata;

	PieData(IMovieData movieData) {
		this.moviedata = movieData;
	}

	@Override
	public HashMap<String, Float> getDataVisualization(List<Review> reviewList) {
		List<String> age_grp = new ArrayList<>();
		for (Review review : reviewList) {
			if (review.getLikablity() == 1) {
				age_grp.add(review.getAge_group());
			}

		}
		HashMap<String, Float> result = moviedata.getAgeGroupCount(age_grp);
		return result;
	}

}
