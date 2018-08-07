package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ErrorControllerTest {

	ErrorController controller = new ErrorController();
	
	@Test
	public void ParserConfigurationExeptionTest() {
		ParserConfigurationException exception = new ParserConfigurationException("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.ParserConfigurationExeption(exception));
	}
	
	@Test
	public void SAXExceptionExeptionTest() {
		SAXException exception = new SAXException("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.SAXExceptionExeption(exception));
	}
	
	@Test
	public void IOExceptionTest() {
		IOException exception = new IOException("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.IOException(exception));
	}
	
	@Test
	public void JSONExceptionTest() {
		JSONException exception = new JSONException("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.JSONException(exception));
	}
	
	@Test
	public void SQLExceptionTest() {
		SQLException exception = new SQLException("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.SQLException(exception));
	}
	
	@Test
	public void ExceptionTest() {
		Exception exception = new Exception("Error");
		ResponseEntity<Object> errorException = new ResponseEntity<>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
		assertEquals(errorException, controller.Exception(exception));
	}
}
