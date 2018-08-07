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
public class BaseAPIUrl implements IAPIUrl {
	
	@Override
	public String getURL() throws IOException, ParserConfigurationException, SAXException, SQLException {
		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String baseURL = ParseAPIXML.Instance().parseXML(props.getProperty("api.xml.baseURLTagName"));
		return baseURL;
	}
}
