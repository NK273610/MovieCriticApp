package com.dalhousie.moviecritic.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalhousie.moviecritic.service.TheatreService;

@RestController
public class TheatreController
{
	@Autowired
	TheatreService theatreService;

	@RequestMapping("/getTheatreDetails")
	public Map<String, Map<String, List<String>>> getTheatreDetails()
    {
		Map<String, Map<String, List<String>>> theatreDetails = theatreService.getTheatreSlotDetails();
			return theatreDetails;
	}
}