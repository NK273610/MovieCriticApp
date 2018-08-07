package com.dalhousie.moviecritic.dao;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.service.PasswordHashingService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.xml.sax.SAXException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReviewDaoTest {

    @InjectMocks
    ReviewDAOImple dao;

    MockData data;

    @Mock
    DataSource mockedDatasource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRatingForMoviesTest()
            throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
        Connection conn = Mockito.mock(Connection.class);
        Mockito.doReturn(conn).when(mockedDatasource).getConnection();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        HashMap<String, Integer> movie1 = new HashMap<String, Integer>() {{
            put("movie1",1);}};
        HashMap<String,HashMap<String,Integer>> expected=new HashMap<>();
        expected.put("user1",movie1);
        Mockito.doReturn("movie1").when(rs).getString("movie_id");
        Mockito.doReturn(1).when(rs).getInt("likablity");
        Mockito.doReturn("user1").when(rs).getString("user_name");
        assertEquals(expected, dao.getRatingForMovies());

    }

    @Test
    public void getRiviewsForMovieTest()
            throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
        Connection conn = Mockito.mock(Connection.class);
        data=new MockData();
        Mockito.doReturn(conn).when(mockedDatasource).getConnection();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Mockito.doReturn(query).when(conn).prepareStatement(Mockito.anyString());

        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        List<Review> list=data.getReviewData();
        Mockito.doReturn("101").when(rs).getString("movie_id");
        Mockito.doReturn("good movie").when(rs).getString("movie_review");
        Mockito.doReturn("21-30").when(rs).getString("age_group");
        Mockito.doReturn(1).when(rs).getInt("likablity");
        Mockito.doReturn("user1").when(rs).getString("user_name");
        Mockito.doReturn(1.0f).when(rs).getFloat("rating");
        assertEquals(data.getReviewData().size()-1, dao.getRiviewsForMovie("1").size());

    }


    @Test
    public void addReview()
            throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException, NoSuchAlgorithmException {
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

        dao.addReview(new Review());

    }

}
