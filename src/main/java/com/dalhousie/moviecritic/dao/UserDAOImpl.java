package com.dalhousie.moviecritic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.Data.User;

@Repository
@ImportResource("classpath:database.xml")
public class UserDAOImpl implements IUserDAO {

    private static Logger logger = LogManager.getLogger(BusinessLogicDAOImpl.class);

    @Autowired
    private DataSource dataSource;
    
    @Override
	public PasswordSalt validateUser(String mail) throws SQLException {
		String sql = "CALL userSaltPass(?)";
		boolean isValid = false;
		String password = null;
		Connection connection = null;
		String salt = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for validateUser() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of validateUser() in " + getClass());
			preparedStatement.setString(1, mail);
			resultSet = preparedStatement.executeQuery();
			logger.info("Result retrieved for validateUser() in " + getClass());
			while (resultSet.next()) {
				password = resultSet.getString("user_pass");
				salt = resultSet.getString("user_salt");
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
		
		PasswordSalt passwordSalt = new PasswordSalt(password, salt);
		return passwordSalt;
	}

    @Override
    public boolean registerUser(User user) {
        String sql = "CALL registerUser(?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for registerUser() in " + getClass());
            preparedStatement = connection.prepareStatement(sql);
            logger.info("Prepared Statement of registerUser() in " + getClass());
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserpass());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getUseremail());
            preparedStatement.setString(6, user.getSalt());
            preparedStatement.executeUpdate();
            logger.info("Query Executed for registerUser() in " + getClass());
            return true;
        } catch (SQLException e) {
            logger.error("Error occured in registerUser() in " + getClass() + " Message: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for registerUser() in " + getClass());
                    preparedStatement.close();
                    logger.info("Prepared Statement of registerUser() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in registerUser() in " + getClass());
                }
            }
        }

        return false;
    }
    
	public boolean getRegisteredUserByEmail(String emailId) {
		boolean isExistingUser = false;
		String sql = "CALL getRegisteredUserByEmail(?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			logger.info(
					"Database connection successfully eastablished for getRegisteredUserByEmail() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of getRegisteredUserByEmail() in " + getClass());
			preparedStatement.setString(1, emailId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				isExistingUser = true;
			}
		} catch (SQLException e) {
			logger.error(
					"Error occured in getRegisteredUserByEmail() in " + getClass() + " Message: " + e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for getRegisteredUserByEmail() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of getRegisteredUserByEmail() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in getRegisteredUserByEmail() in " + getClass());
				}
			}
		}
		return isExistingUser;
	}

	public void updatePasswordByEmailId(String emailId, String newPassword, String salt) {
		String sql = "CALL updatePasswordByEmailId(?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			logger.info(
					"Database connection successfully eastablished for updatePasswordByEmailId() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of updatePasswordByEmailId() in " + getClass());
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, salt);
			preparedStatement.setString(3, emailId);	
			preparedStatement.executeUpdate();
			logger.info("Password updated successfully in database : " + getClass());
			
		} catch (SQLException e) {
			logger.error(
					"Error occured in updatePasswordByEmailId() in " + getClass() + " Message: " + e.getMessage());
		}finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for updatePasswordByEmailId() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of updatePasswordByEmailId() successfully closed in " + getClass());

				} catch (SQLException e) {
					logger.error("Error occured in updatePasswordByEmailId() in " + getClass() + " Message: " + e.getMessage());
				}
			}
		}
		
	}
	
	@Override
	public User getUsername(User user) throws SQLException {
		User databaseValUser = new User();
		String sql = "CALL getUserName(?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for getUsername() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of getUsername() in " + getClass());
			preparedStatement.setString(1, user.getUseremail());
			resultSet = preparedStatement.executeQuery();
			logger.info("Query Executed for getUsername() in " + getClass());
			while (resultSet.next()) {
				databaseValUser.setFirstname(resultSet.getString("firstname"));
				databaseValUser.setLastname(resultSet.getString("lastname"));
				databaseValUser.setUsername(resultSet.getString("user_name"));
			}
		} catch (SQLException e) {
			logger.error("Error occured in getUsername() in " + getClass() + " Message: " + e.getMessage());
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for getUsername() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of getUsername() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in getUsername() in " + getClass());
				}
			}
		}
		return databaseValUser;
	}

	public User getUserProfileByUseName(String userName) {
		User user = new User();
		String sql = "CALL getRegisteredUserByUsername(?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			logger.info(
					"Database connection successfully eastablished for getUserProfileByUseName() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of getUserProfileByUseName() in " + getClass());
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setFirstname(rs.getString("user_name"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setUseremail(rs.getString("user_email"));
				user.setImagePath(rs.getString("image_path"));
				user.setUsername(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			logger.error(
					"Error occured in getRegisteredUserByEmail() in " + getClass() + " Message: " + e.getMessage());
		}finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for getUsername() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of getUsername() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in getUsername() in " + getClass());
				}
			}
		}
		
		return user;
	}

	public void UpdateUserProfileByUserName(String firstname, String lastname, String imagePath, String username) {
		String sql = "CALL UpdateUserProfileByUserName(?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, imagePath);
			preparedStatement.setString(4, username);
			int result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error occured in updatePasswordByEmailId() in " + getClass() + " Message: " + e.getMessage());
		}finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for getUsername() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of getUsername() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in getUsername() in " + getClass());
				}
			}
		}
		
	}

    public boolean getRegisteredUserByUsername(String username) {
		boolean isExistingUser = false;
		String sql = "CALL getRegisteredUserByUsername(?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			connection = dataSource.getConnection();
			logger.info(
					"Database connection successfully eastablished for getRegisteredUserByUsername() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of getRegisteredUserByUsername() in " + getClass());
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				isExistingUser = true;
			}
		} catch (SQLException e) {
			logger.error(
					"Error occured in getRegisteredUserByUsername() in " + getClass() + " Message: " + e.getMessage());
		}
		return isExistingUser;
    }
    
    @Override
    public User getUserDetails(String user_name){
        User user = new User();
        String userdetails = "CALL getUserDetails(?)";
        Connection connection = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet2 = null;
        try {

        	connection = dataSource.getConnection();
            logger.info("Database connection successfully eastablished for getUserDetails() in " + getClass());
            preparedStatement2 = connection.prepareStatement(userdetails);
            logger.info("Prepared Statement of getUserDetails() in " + getClass());
             preparedStatement2.setString(1, user_name);
            resultSet2 = preparedStatement2.executeQuery();
            logger.info("Result retrieved for getUserDetails() in " + getClass());
            while (resultSet2.next()) {
                user.setFirstname(resultSet2.getString("firstname"));
                user.setLastname(resultSet2.getString("lastname"));
            }

        } catch (SQLException e) {
            logger.error("Error occured in getUserDetails() in " + getClass() + " Message: " + e.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Error occured in getUserDetails() in " + getClass() + " Message: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Database connection successfully closed for getUserDetails() in " + getClass());

                    logger.info("Prepared Statement of getUserDetails() successfully closed in " + getClass());
                    preparedStatement2.close();
                    logger.info("Prepared Statement2 of getUserDetails() successfully closed in " + getClass());

                    logger.info("ResultSet of getUserDetails() successfully closed in " + getClass());
                    resultSet2.close();
                    logger.info("ResultSet2 of getUserDetails() successfully closed in " + getClass());
                } catch (SQLException e) {
                    logger.error("Error occured in getUserDetails() in " + getClass() + " Message: " + e.getMessage());
                }
            }
        }

        return user;
    }
    
    @Override
	public int changePassword(User user) throws SQLException {
		String sql = "CALL ChangePassword (?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			connection = dataSource.getConnection();
			logger.info("Database connection successfully eastablished for changePassword() in " + getClass());
			preparedStatement = connection.prepareStatement(sql);
			logger.info("Prepared Statement of changePassword() in " + getClass());
			preparedStatement.setString(1, user.getUserpass());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getUseremail());
			result = preparedStatement.executeUpdate();
			logger.info("Query Executed for changePassword() in " + getClass());
		} catch (SQLException e) {
			logger.error("Error occured in changePassword() in " + getClass() + " Message: " + e.getMessage());
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
					logger.info("Database connection successfully closed for changePassword() in " + getClass());
					preparedStatement.close();
					logger.info("Prepared Statement of changePassword() successfully closed in " + getClass());
				} catch (SQLException e) {
					logger.error("Error occured in changePassword() in " + getClass());
				}
			}
		}
		return result;
	}
    
}
