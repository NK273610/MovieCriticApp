package com.dalhousie.moviecritic.Data;

public class Review {

    String movie_id;
    String movieName;
    String reviews;
    String user_name;
    float rating;
    int likablity;
    String age_group;

    public Review() {
    }

    public Review(String movie_id, String reviews, String user_name, float rating, int likablity, String age_group) {
        this.movie_id = movie_id;
        this.reviews = reviews;
        this.rating = rating;
        this.likablity = likablity;
        this.age_group = age_group;
        this.user_name = user_name;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getLikablity() {
        return likablity;
    }

    public void setLikablity(int likablity) {
        this.likablity = likablity;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
