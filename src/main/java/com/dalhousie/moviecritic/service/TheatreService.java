package com.dalhousie.moviecritic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dalhousie.moviecritic.Data.SlotTime;
import com.dalhousie.moviecritic.Data.Theatre;
import com.dalhousie.moviecritic.dao.ITheatrePageDao;

@Service
public class TheatreService implements ITheatreService {

	@Autowired
	ITheatrePageDao theatredao;

	@Override
	public List<Theatre> getTheatreList(String movieid) {
		return theatredao.getTheatresForMovie(movieid);
	}

	public Map<String, Map<String, List<String>>> getTheatreSlotDetails() {
		List<SlotTime> theatre = theatredao.getTheatresForSlotTime();
		Map<String, Map<String, List<String>>> theatreMap = new HashMap<String, Map<String, List<String>>>();
		for (SlotTime slotTime : theatre) {
			if (theatreMap.containsKey(slotTime.getTheaterName())) {
				fillMovieMap(theatreMap.get(slotTime.getTheaterName()), slotTime);
			} else {
				Map<String, List<String>> movieMap = new HashMap<String, List<String>>();
				fillMovieMap(movieMap, slotTime);
				theatreMap.put(slotTime.getTheaterName(), movieMap);
			}
		}
		return theatreMap;
	}

	private void fillMovieMap(Map<String, List<String>> movieMap, SlotTime slotTime) {
		if (movieMap.containsKey(slotTime.getMovieName())) {
			movieMap.get(slotTime.getMovieName()).addAll(getMovieSlots(slotTime.getSlotTime()));
		} else {
			movieMap.put(slotTime.getMovieName(), getMovieSlots(slotTime.getSlotTime()));
		}
	}

	List<String> getMovieSlots(String slotTime) {
		List<String> slotList = Arrays.asList(slotTime.split(","));
		List<String> slots = new ArrayList<String>();
		for (String obj : slotList) {
			slots.add((String) obj);
		}
		return slots;
	}

}