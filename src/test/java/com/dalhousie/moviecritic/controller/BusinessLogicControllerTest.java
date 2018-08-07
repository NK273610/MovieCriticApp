package com.dalhousie.moviecritic.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.dao.BusinessLogicDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessLogicControllerTest {
	
	@InjectMocks
	BusinessLogicController controller = new BusinessLogicController();
	
	@Mock
    BusinessLogicDAOImpl dao = new BusinessLogicDAOImpl();
	
	@Test
	public void registrationconfigTest() throws SQLException, IOException {
		HashMap<String, String> registrationConfig = new HashMap<>();
		registrationConfig.put("1", "1");
		Mockito.doReturn(registrationConfig).when(dao).fetchFormValidationPattern();
		
		assertNotNull(controller.registrationconfig());
	}

}
