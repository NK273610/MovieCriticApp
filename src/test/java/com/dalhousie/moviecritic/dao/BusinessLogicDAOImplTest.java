package com.dalhousie.moviecritic.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessLogicDAOImplTest {

	@InjectMocks
	BusinessLogicDAOImpl dao;
	
	@Mock
	DataSource mockedDatasource;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void fetchFormValidationPatternTest() throws SQLException {

		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		HashMap<String, String> formValidation = new HashMap<>();
		formValidation.put("test1", "test2");
		Mockito.doReturn("test1").when(rs).getString("input");
		Mockito.doReturn("test2").when(rs).getString("value");
		assertEquals(formValidation, dao.fetchFormValidationPattern());
	
	}
	
	@Test(expected=SQLException.class)
	public void fetchFormValidationPatternExceptionTest() throws SQLException {

		Connection conn = Mockito.mock(Connection.class);
		Mockito.doReturn(conn).when(mockedDatasource).getConnection();
		PreparedStatement query = Mockito.mock(PreparedStatement.class);
		Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenThrow(new SQLException());
		HashMap<String, String> formValidation = new HashMap<>();
		formValidation.put("test1", "test2");
		Mockito.doReturn("test1").when(rs).getString("input");
		Mockito.doReturn("test2").when(rs).getString("value");
		assertEquals(formValidation, dao.fetchFormValidationPattern());
	
	}
	
}
