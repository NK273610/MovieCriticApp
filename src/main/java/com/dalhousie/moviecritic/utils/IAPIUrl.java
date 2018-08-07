package com.dalhousie.moviecritic.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface IAPIUrl {

	public String getURL() throws IOException, ParserConfigurationException, SAXException, SQLException;
	
}
