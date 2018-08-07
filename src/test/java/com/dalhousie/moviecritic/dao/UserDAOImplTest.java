package com.dalhousie.moviecritic.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.MockData.MockData;
import org.mockito.runners.MockitoJUnitRunner;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.service.PasswordHashingService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOImplTest {

	@InjectMocks
	UserDAOImpl dao;

	MockData data;
	User user = new User();
	
	@Mock
	DataSource mockedDatasource;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validateuserTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("5678");
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.doReturn(hashPassword.getHashPassword()).when(rs).getString("user_pass");
		Mockito.doReturn(hashPassword.getSalt()).when(rs).getString("user_salt");

		assertEquals(hashPassword.getHashPassword(), dao.validateUser("ankit").getHashPassword());
	}
	
	@Test
	public void validateuserNotNullTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("5678");
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.doReturn(hashPassword.getHashPassword()).when(rs).getString("user_pass");
		Mockito.doReturn(hashPassword.getSalt()).when(rs).getString("user_salt");
		assertNotNull(dao.validateUser("ankit"));
	}

	@Test
	public void registerUserTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException
	{
		Connection conn = Mockito.mock(Connection.class);
		data=new MockData();
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

		Mockito.doNothing().when(query).setString(1,"test");
		Mockito.doNothing().when(query).setString(2,"test2");
		Mockito.doNothing().when(query).setString(3,"test3");
		Mockito.doNothing().when(query).setString(4,"test4");
		Mockito.doNothing().when(query).setString(5,"test5");
		Mockito.doNothing().when(query).setString(6,"test6");

		assertEquals(true,dao.registerUser(user));

	}
	
	@Test
	public void changePasswordTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeUpdate()).thenReturn(1);
		assertEquals(1, dao.changePassword(user));
	}
	
	@Test(expected=SQLException.class)
	public void changePasswordExceptionTest()
			throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeUpdate()).thenThrow(new SQLException());
		assertEquals(1, dao.changePassword(user));
	}

}