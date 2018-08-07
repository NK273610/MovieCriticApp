package com.dalhousie.moviecritic.Data;

public class Theatre {
	
	String movie_id;
    String theatre_name;
    String showtime;
 

    public Theatre(){
    	
    }
    
    public Theatre(String movie_id, String theatre_name, String showtime, String movie_name) {
        this.movie_id = movie_id;
        this.theatre_name = theatre_name;
        this.showtime = showtime;

    }

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getTheatre_name() {
		return theatre_name;
	}

	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}


       
}
