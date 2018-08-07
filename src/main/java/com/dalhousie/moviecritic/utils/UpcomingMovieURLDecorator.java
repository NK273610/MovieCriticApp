package com.dalhousie.moviecritic.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class UpcomingMovieURLDecorator extends URLDecorator {

	private String UpcomingMovieURL;

	public UpcomingMovieURLDecorator(IAPIUrl decoratedApiUrl) {
		super(decoratedApiUrl);
	}
	
	@Override
	public String getURL() throws IOException, ParserConfigurationException, SAXException, SQLException {
		// TODO Auto-generated method stub
		String baseUrl = url.getURL();
		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		UpcomingMovieURL = baseUrl + 
				ParseAPIXML.Instance().parseXML(props.getProperty("api.xml.upcomingURLTagName"));		
		return UpcomingMovieURL;
	}



}
