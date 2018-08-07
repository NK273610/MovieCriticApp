package com.dalhousie.moviecritic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ParseAPIXML {
	
	private static ParseAPIXML instance = null;
	
	public static ParseAPIXML Instance() {
		if (null == instance) {
			instance = new ParseAPIXML();
		}
		return instance;
	}
	
	private ParseAPIXML() {
	}
	
	
	public String parseXML(String tagName) throws IOException, ParserConfigurationException, SAXException {
		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		InputStream inputStreamFromXML = new ClassPathResource(props.getProperty("api.xml.filename")).getInputStream();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document xmlDocment = documentBuilder.parse(inputStreamFromXML);
		return parseTagInXML(xmlDocment, tagName);
	}

	public String parseTagInXML(Document xmlDocment, String tagName) {
		String url = xmlDocment.getElementsByTagName(tagName).item(0).getTextContent();
		if(url.contains("API_KEY")) {
			String key = xmlDocment.getElementsByTagName("key").item(0).getTextContent();
			url = url.replace("API_KEY", key);
			return url;
		}else {
			return url;
		}
		
	}

}
