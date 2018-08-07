package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.Theatre;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.dao.TheatrePageDao;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-dev.properties")
public class TheatreServiceTest {

	MockData data;
	
	@Value("${movie.test.slottime}")
	String timeSlots;
	
	@Mock
	TheatrePageDao theatredao;


	@InjectMocks
	TheatreService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTheatreList() {
		MockData  data  = new MockData();
		Mockito.doReturn(data.getTheatresForMovie("1")).when(theatredao).getTheatresForMovie("1");
		assertEquals(new ArrayList<Theatre>(), service.getTheatreList("1"));
	}


	@Test
	public void getTheatreSlotDetails() {
		data = new MockData();
		Mockito.when(theatredao.getTheatresForSlotTime()).thenReturn(data.getTheatresForSlotTime());
		assertNotNull(service.getTheatreSlotDetails());
	}
	@Test
	public void getMovieSlots() {
		assertNotNull(service.getMovieSlots(timeSlots));
	}

}
