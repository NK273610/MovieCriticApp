package com.dalhousie.moviecritic.dao;

import com.dalhousie.moviecritic.Data.SlotTime;
import com.dalhousie.moviecritic.Data.Theatre;
import java.util.List;

public interface ITheatrePageDao {

	 public List<Theatre> getTheatresForMovie(String movieid);

	public List<SlotTime> getTheatresForSlotTime();
}



