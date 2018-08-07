package com.dalhousie.moviecritic.lower;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.utils.BaseAPIUrl;
import com.dalhousie.moviecritic.utils.MovieDetailURLDecorator;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDetailURLDecoratorTest {

	MovieDetailURLDecorator url = new MovieDetailURLDecorator(new BaseAPIUrl());
	
	@Test
	public void getUrlTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {
		assertNotNull(url.getURL());
	}
	
	
}
