package com.dalhousie.moviecritic.controller;

import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.xml.sax.SAXException;

@ControllerAdvice
public class ErrorController
{
	private static Logger logger = LogManager.getLogger(ErrorController.class);
	
	@ExceptionHandler(ParserConfigurationException.class)
	public ResponseEntity<Object> ParserConfigurationExeption(ParserConfigurationException exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(SAXException.class)
	public ResponseEntity<Object> SAXExceptionExeption(SAXException exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(java.io.IOException.class)
	public ResponseEntity<Object> IOException(java.io.IOException exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(JSONException.class)
	public ResponseEntity<Object> JSONException(JSONException exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> SQLException(SQLException exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> Exception(Exception exception)
	{
		logger.error("Exception occured:" + exception);
		return new ResponseEntity<Object>("Service Unavailable! Please try again later", HttpStatus.SERVICE_UNAVAILABLE);
	}
}