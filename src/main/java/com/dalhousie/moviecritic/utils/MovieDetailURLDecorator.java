package com.dalhousie.moviecritic.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.xml.sax.SAXException;

public class MovieDetailURLDecorator extends URLDecorator {

	private String movieDetailsByIDURL;

	public MovieDetailURLDecorator(IAPIUrl decoratedApiUrl) {
		super(decoratedApiUrl);
	}

	@Override
	public String getURL() throws IOException, ParserConfigurationException, SAXException, SQLException {
		String baseUrl = url.getURL();
		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		movieDetailsByIDURL = baseUrl
				+ ParseAPIXML.Instance().parseXML(props.getProperty("api.xml.moviedetailsByIDTagName"));
		return movieDetailsByIDURL;	
	}

}
