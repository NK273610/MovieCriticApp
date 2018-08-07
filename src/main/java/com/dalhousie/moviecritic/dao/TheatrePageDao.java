package com.dalhousie.moviecritic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import com.dalhousie.moviecritic.Data.SlotTime;
import com.dalhousie.moviecritic.Data.Theatre;
import com.dalhousie.moviecritic.Data.TheatreFactory;

@Repository
@ImportResource("classpath:database.xml")
public class TheatrePageDao implements ITheatrePageDao {
	
private static Logger logger = LogManager.getLogger(TheatrePageDao.class);
    
    @Autowired
    private DataSource dataSource;
    @Override
    public List<Theatre> getTheatresForMovie(String movieid) {
        String sql = "CALL getTheatresForMovie(?)";
        List<Theatre> theatres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
      
        try {
        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for getTheatresForMovie() in " + getClass());
            preparedStatement = connection.prepareStatement(sql);
            logger.info("Prepared Statement of getTheatresForMovie() in " + getClass());
            preparedStatement.setString(1, movieid);
            resultSet = preparedStatement.executeQuery();
            logger.info("Result retrieved for getTheatresForMovie() in " + getClass());
            while (resultSet.next()) {
                Theatre theatreobj = TheatreFactory.getTheatreObject();
                theatreobj.setMovie_id(resultSet.getString("movie_id"));
                theatreobj.setShowtime(resultSet.getString("showtime"));
                theatreobj.setTheatre_name(resultSet.getString("theatre_name"));
                theatres.add(theatreobj);
               
                }

        } catch (SQLException e) {
           logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getTheatresForMovie() in " + getClass());
                    preparedStatement.close();
                    logger.info("Prepared Statement of getTheatresForMovie() successfully closed in " + getClass());
                    resultSet.close();
                    logger.info("ResultSet of getTheatresForMovie() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }
        
        return theatres;
    }
    
    public List<SlotTime> getTheatresForSlotTime() {
		String sql = "CALL getTheatresForSlotTime()";
		List<SlotTime> slotTimes = new ArrayList<SlotTime>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for getTheatresForMovie() in " + getClass());
			statement = connection.createStatement();
			logger.info("Prepared Statement of getTheatresForMovie() in " + getClass());
			resultSet=statement.executeQuery(sql);
			logger.info("Result retrieved for getTheatresForMovie() in " + getClass());
			while (resultSet.next()) {
				SlotTime slotTime = TheatreFactory.getSlotTimeObject();
				slotTime.setMovieName(resultSet.getString("movie_name"));
				slotTime.setTheaterName(resultSet.getString("theaterName"));
				slotTime.setSlotTime(resultSet.getString("slot_time"));
				slotTimes.add(slotTime);
			}

		} catch (SQLException e) {
			logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
		}
		finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getTheatresForMovie() in " + getClass());
                    statement.close();
                    logger.info("Prepared Statement of getTheatresForMovie() successfully closed in " + getClass());
                    resultSet.close();
                    logger.info("ResultSet of getTheatresForMovie() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getTheatresForMovie() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }

		return slotTimes;
	}
}