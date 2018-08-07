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

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.MockData.MockData;

@RunWith(SpringJUnit4ClassRunner.class)
public class TheatreDaoTest {

	MockData mock;

	@InjectMocks
	TheatrePageDao dao;

	@Mock
	TheatrePageDao dao1;

	@Mock
	DataSource mockedDatasource;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTheatresForMovie() throws ParserConfigurationException, SAXException, IOException, JSONException,
			SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.doReturn("1").when(rs).getString("movie_id");
		Mockito.doReturn("12PM").when(rs).getString("showtime");
		Mockito.doReturn("testTheatre").when(rs).getString("theatre_name");
		assertNotNull(dao.getTheatresForMovie("1"));
	}

	@Test
	public void getTheatresForSlotTime() throws ParserConfigurationException, SAXException, IOException, JSONException,
			SQLException, NoSuchAlgorithmException {
		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.doReturn("movie1").when(rs).getString("movie_name");
		Mockito.doReturn("12PM").when(rs).getString("slot_time");
		Mockito.doReturn("testTheatre").when(rs).getString("theatre_name");
		assertNotNull(dao.getTheatresForSlotTime());
	}

}
