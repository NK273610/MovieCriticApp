package com.dalhousie.moviecritic.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public abstract class URLDecorator implements IAPIUrl {

	protected IAPIUrl url;
	
	
	public URLDecorator(IAPIUrl url) {
		super();
		this.url = url;
	}


	@Override
	public abstract String getURL() throws IOException, ParserConfigurationException, SAXException, SQLException;

}
