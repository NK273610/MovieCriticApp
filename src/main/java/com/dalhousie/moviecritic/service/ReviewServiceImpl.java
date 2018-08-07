package com.dalhousie.moviecritic.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.Movie;
import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.dao.IReviewDao;
import com.dalhousie.moviecritic.utils.HashMapSorting;
import com.dalhousie.moviecritic.utils.IBadLanguage;
import com.dalhousie.moviecritic.utils.IMovieData;

@Service
@Component
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	IReviewDao moviepagedao;

	@Autowired
	IMovieData moviedata;

	@Autowired
	ChartDataFactory chartfactory;

	@Autowired
	IMovieService movieService;

	@Autowired
	IBadLanguage badLanguage;

	@Autowired
	IReviewDao reviewDao;

	@Override
	public List<Review> getMovieData(String movieid) {
		return moviepagedao.getRiviewsForMovie(movieid);
	}

	@Override
	public boolean validateReview(String review) {
		boolean flag = false;
		try {
			flag = badLanguage.badLanguageWords(review);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void addReview(Review review) {

		reviewDao.addReview(review);

	}

	@Override
	public List<Review> getUserReviews(String user_name)
			throws SQLException, ParserConfigurationException, SAXException, JSONException, IOException {
		List<Review> reviewList = moviepagedao.getUserReviews(user_name);
		List<Review> reviewListWithMovieName = new ArrayList<>();
		for (Review r : reviewList) {
			Review reviewWithMovieName = new Review();
			List<Movie> movie = movieService.getMovieDetails(r.getMovie_id());
			reviewWithMovieName.setMovieName(movie.get(0).getTitle());
			reviewWithMovieName.setReviews(r.getReviews());
			reviewWithMovieName.setRating(r.getRating());
			reviewWithMovieName.setMovie_id(r.getMovie_id());
			reviewListWithMovieName.add(reviewWithMovieName);
		}
		return reviewListWithMovieName;
	}

	@Override
	public List<String> getMovieRecommendForUser(String userid) {
		HashMap<String, HashMap<String, Integer>> fullist = moviepagedao.getRatingForMovies();
		HashMap<String, Float> recommendlist = moviedata.getMovie(fullist, userid);
		recommendlist = (HashMap<String, Float>) HashMapSorting.sortByComparator(recommendlist);

		List<String> movielist = new ArrayList<>();
		Iterator<String> it = recommendlist.keySet().iterator();
		int i = 0;
		while (it.hasNext() && i < 5) {
			HashMap<String, Integer> movie = fullist.get(it.next());
			for (String usermovie : movie.keySet()) {
				if (movielist.contains(usermovie)) {
					continue;
				} else {
					movielist.add(usermovie);
				}
			}
			i++;
		}

		return movielist;
	}

	@Override
	public HashMap<String, Float> getUniqueWords(String movieid) {
		List<Review> reviewList = moviepagedao.getRiviewsForMovie(movieid);
		HashMap<String, Float> result = chartfactory.getData("WordCloud").getDataVisualization(reviewList);
		return (HashMap<String, Float>) HashMapSorting.sortByComparator(result);
	}

	@Override
	public float getRating(String movieid) {

		List<Review> movierating = moviepagedao.getRiviewsForMovie(movieid);
		List<Float> rating = new ArrayList<>();
		for (Review review : movierating) {
			rating.add(review.getRating());
		}
		float result = (float) moviedata.CalculateRating(rating);
		DecimalFormat df = new DecimalFormat("#.#");
		return Float.parseFloat(df.format(result));
	}

	@Override
	public HashMap<String, Float> getAgeGroup(String movieid) {

		List<Review> reviewList = moviepagedao.getRiviewsForMovie(movieid);
		HashMap<String, Float> result = chartfactory.getData("PieChart").getDataVisualization(reviewList);
		return (HashMap<String, Float>) HashMapSorting.sortByComparator(result);
	}

	public HashMap<String, Float> getAgeGroupRating(String movieid) {

		List<Review> reviewList = moviepagedao.getRiviewsForMovie(movieid);
		HashMap<String, Float> result = chartfactory.getData("BarChart").getDataVisualization(reviewList);
		return (HashMap<String, Float>) HashMapSorting.sortByComparator(result);
	}
}
