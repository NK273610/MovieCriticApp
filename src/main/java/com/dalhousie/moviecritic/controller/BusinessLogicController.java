package com.dalhousie.moviecritic.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dalhousie.moviecritic.dao.IBusinessLogicDAO;

@Controller
public class BusinessLogicController
{
	@Autowired
	private IBusinessLogicDAO dao;
	
	@RequestMapping(value="/registrationconfig")
	public @ResponseBody Map<String, String> registrationconfig() throws IOException, SQLException
	{
		return dao.fetchFormValidationPattern();
	}
}
