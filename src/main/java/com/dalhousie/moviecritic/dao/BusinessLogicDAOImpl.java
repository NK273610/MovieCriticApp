package com.dalhousie.moviecritic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

@Repository
@ImportResource("classpath:database.xml")
public class BusinessLogicDAOImpl implements IBusinessLogicDAO {

	private static Logger logger = LogManager.getLogger(BusinessLogicDAOImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public Map<String, String> fetchFormValidationPattern() throws SQLException {
		String sql = "select input, value from form";
		Connection connection = null;
		String baseURL = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, String> formValidationPattern = new HashMap<>();

		try {

			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for validateUser() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of validateUser() in " + getClass());
			resultSet = preparedStatement.executeQuery();
			logger.info("Result retrieved for validateUser() in " + getClass());
			while (resultSet.next()) {
				String input = resultSet.getString("input");
				String value = resultSet.getString("value");
				formValidationPattern.put(input, value);
			}

		} catch (SQLException e) {
			logger.error("Error occured in validateUser() in " + getClass() + " Message: " + e.getMessage());
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for validateUser() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of validateUser() successfully closed in " + getClass());
					resultSet.close();
					logger.info("ResultSet of validateUser() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in validateUser() in " + getClass() + " Message: " + e.getMessage());
				}
			}
		}

		return formValidationPattern;
	}

}
