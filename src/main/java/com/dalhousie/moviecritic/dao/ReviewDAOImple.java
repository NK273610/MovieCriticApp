package com.dalhousie.moviecritic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import com.dalhousie.moviecritic.Data.Review;

@Repository
@ImportResource("classpath:database.xml")
public class ReviewDAOImple implements IReviewDao{

    private static Logger logger = LogManager.getLogger(ReviewDAOImple.class);

    @Autowired
    private DataSource dataSource;
    
    @Override
	public void addReview(Review review) {
		String sql = "CALL addReviewInsert(?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for addReview() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of addReview() in " + getClass());
			preparedStatement.setString(1,review.getUser_name());
			preparedStatement.setString(2, review.getMovie_id());
			preparedStatement.setString(3, review.getReviews());
			preparedStatement.setFloat(4, review.getRating());
			preparedStatement.setInt(5, review.getLikablity());
			preparedStatement.setString(6, review.getAge_group());
			preparedStatement.executeUpdate();
			logger.info("Query Executed for addReview() in " + getClass());
		} catch (SQLException e) {
			logger.error("Error occured in addReview() in " + getClass() + " Message: " + e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for addReview() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of addReview() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in addReview() in " + getClass());
				}
			}
		}

	}

    @Override
    public List<Review> getRiviewsForMovie(String movieid) {
        String sql = "CALL getRiviewsForMovie(?)";
        List<Review> reviews = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for getRiviewsForMovie() in " + getClass());
            preparedStatement = connection.prepareStatement(sql);
            logger.info("Prepared Statement of getRiviewsForMovie() in " + getClass());
            preparedStatement.setString(1, movieid);
            resultSet = preparedStatement.executeQuery();
            logger.info("Result retrieved for getRiviewsForMovie() in " + getClass());
            while (resultSet.next()) {
                Review reviewobj=new Review();
                reviewobj.setAge_group(resultSet.getString("age_group"));
                reviewobj.setUser_name(resultSet.getString("user_name"));
                reviewobj.setLikablity(resultSet.getInt("likablity"));
                reviewobj.setReviews(resultSet.getString("movie_review"));
                reviewobj.setMovie_id(resultSet.getString("movie_id"));
                reviewobj.setRating(resultSet.getFloat("rating"));
                reviews.add(reviewobj);

            }

        } catch (SQLException e) {
            logger.error("Error occured in getRiviewsForMovie() in " + getClass() + " Message: " + e.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Error occured in getRiviewsForMovie() in " + getClass() + " Message: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getRiviewsForMovie() in " + getClass());
                    preparedStatement.close();
                    logger.info("Prepared Statement of getRiviewsForMovie() successfully closed in " + getClass());
                    resultSet.close();
                    logger.info("ResultSet of getRiviewsForMovie() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getRiviewsForMovie() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }

        return reviews;
    }
    @Override
    public HashMap<String,HashMap<String,Integer>> getRatingForMovies() {

        String sql = "CALL getAllReviews ()";
        HashMap<String,HashMap<String,Integer>> all_movie = new HashMap<String,HashMap<String,Integer>>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for getRatingForMovies() in " + getClass());
            preparedStatement = connection.prepareStatement(sql);
            logger.info("Prepared Statement of getRatingForMovies() in " + getClass());
            resultSet = preparedStatement.executeQuery();
            logger.info("Result retrieved for getRatingForMovies() in " + getClass());
            while (resultSet.next()) {
                if(all_movie.containsKey(resultSet.getString("user_name")))
                {
                    all_movie.get(resultSet.getString("user_name")).put(resultSet.getString("movie_id"),
                            resultSet.getInt("likablity"));
                }
                else
                {
                    HashMap<String,Integer>temp=new HashMap<>();
                    temp.put(resultSet.getString("movie_id"),resultSet.getInt("likablity"));
                    all_movie.put(resultSet.getString("user_name"),temp);
                }
            }

        } catch (SQLException e) {
            logger.error("Error occured in getRatingForMovies() in " + getClass() + " Message: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getRatingForMovies() in " + getClass());
                    preparedStatement.close();
                    logger.info("Prepared Statement of getRatingForMovies() successfully closed in " + getClass());
                    resultSet.close();
                    logger.info("ResultSet of getRatingForMovies() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getRatingForMovies() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }
        return all_movie;
    }


    @Override
    public List<Review> getUserReviews(String user_name) {
        String userreview = "CALL getUserReview(?)";
        List<Review> reviews = new ArrayList<Review>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for getUserReviews() in " + getClass());
            preparedStatement = connection.prepareStatement(userreview);


            logger.info("Prepared Statement of getUserReviews() in " + getClass());
            preparedStatement.setString(1, user_name);
            resultSet = preparedStatement.executeQuery();

              logger.info("Result retrieved for getUserReviews() in " + getClass());
            Review review= null;
            while (resultSet.next()) {
                review = new Review();
                review.setMovie_id(resultSet.getString("movie_id"));
                review.setReviews(resultSet.getString("movie_review"));
                review.setRating(resultSet.getFloat("rating"));
                review.setUser_name(resultSet.getString("user_name"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            logger.error("Error occured in getUserReviews() in " + getClass() + " Message: " + e.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Error occured in getUserReviews() in " + getClass() + " Message: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getUserReviews() in " + getClass());
                    preparedStatement.close();
                    logger.info("Prepared Statement of getUserReviews() successfully closed in " + getClass());
                    logger.info("Prepared Statement2 of getUserReviews() successfully closed in " + getClass());
                    resultSet.close();
                    logger.info("ResultSet of getUserReviews() successfully closed in " + getClass());

                    logger.info("ResultSet2 of getUserReviews() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getUserReviews() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }

        return reviews;
    }
    

}


