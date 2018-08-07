package com.dalhousie.moviecritic.MockData;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.Data.SlotTime;
import com.dalhousie.moviecritic.Data.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockData {

    public HashMap<String,HashMap<String,Float>> getFullList() {
        HashMap<String, HashMap<String, Float>> fulllist = new HashMap<>();
        HashMap<String, Float> user1Movie = new HashMap<String, Float>() {
            {
                put("movie1", 1.0f);
                put("movie2", 0.f);}
            ;};
        HashMap<String, Float> user2Movie = new HashMap<String, Float>() {
            {
                put("movie1", 1.0f);
                put("movie3", 0.f);}
            ;};
        HashMap<String, Float> user3Movie = new HashMap<String, Float>() {
            {
                put("movie3", 1.0f);
                put("movie4", 0.f);}
            ;};
        fulllist.put("user1",user1Movie);
        fulllist.put("user2",user2Movie);
        fulllist.put("user3",user3Movie);
    return fulllist;
    }

    public HashMap<String,Float> getMovie()
    {
        HashMap<String, Float> movie = new HashMap<String, Float>() {
            {
                put("user2", 1.0f);
                put("user3", 2.f);
                }
            ;};

        return movie;
    }

    public List<Review> getReviewData()
    {
        List<Review> arr=new ArrayList<>();
        Review rev1=new Review();
        rev1.setMovie_id("101");
        rev1.setUser_name("user1");
        rev1.setReviews("good movie");
        rev1.setAge_group("21-30");
        rev1.setMovieName("Ant Man");
        rev1.setLikablity(1);
        rev1.setRating(1.0f);
        Review rev2=new Review();
        rev2.setMovie_id("102");
        rev2.setUser_name("user2");
        rev2.setReviews("nice movie");
        rev2.setAge_group("21-30");
        rev2.setMovieName("God Man");
        rev2.setLikablity(1);
        rev2.setRating(1.0f);
        arr.add(rev1);
        arr.add(rev2);

        return arr;

    }
    public List<SlotTime> getTheatresForSlotTime(){
    	List<SlotTime> list = new ArrayList<SlotTime>();
    	SlotTime slot = new SlotTime();
    	slot.setMovieName("movie1");
    	slot.setSlotTime("12AM");
    	slot.setTheaterName("theatre1");
    	list.add(slot);
    	return list;
    }
    
    public List<Theatre>  getTheatresForMovie(String movieid) {
    	List<Theatre> list = new ArrayList<Theatre>();
    	Theatre theatre = new Theatre();
    	theatre.setMovie_id("1");
    	theatre.setShowtime("12AM");
    	theatre.setTheatre_name("test");
    	return list;
    }
    
    public Map<String, Map<String, List<String>>> getTheatreSlotDetails(){
    	Map<String, Map<String, List<String>>> theatreMap = new HashMap<String, Map<String, List<String>>>();
    	Map<String, List<String>> movie = new HashMap<String, List<String>>();
    	List<String> slot = new ArrayList<String>();
    	slot.add("12PM");
    	slot.add("3PM");
    	movie.put("testMovie", slot);
    	theatreMap.put("testTheatre", movie);
    	return theatreMap;
    	}

    
}
