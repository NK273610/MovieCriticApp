package com.dalhousie.moviecritic.service;

import java.util.List;
import java.util.Map;

import com.dalhousie.moviecritic.Data.Theatre;

public interface ITheatreService {
	public List<Theatre> getTheatreList(String movieid);

	Map<String, Map<String, List<String>>> getTheatreSlotDetails();
}
