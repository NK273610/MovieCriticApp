package com.dalhousie.moviecritic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.service.IUserProfileService;
import com.dalhousie.moviecritic.service.IUserService;

@RestController
public class UserProfileController {
	
	 @Autowired
	 IUserService userRegisterService;
	 
	@Autowired
	private IUserProfileService userProfileService;

	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	public User getUserProfile() throws SQLException {
		User loggedInUser = new User();
		loggedInUser.setUseremail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		User databaseValUser = userRegisterService.getUsername(loggedInUser);
		User userProfileDetails = userProfileService.getUserProfileDetails(databaseValUser.getUsername());
		return userProfileDetails;
	}

	@ResponseBody
	@RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
	public String updateUserProfile(@RequestParam(value = "profileImage") MultipartFile profileImage,
			@RequestPart("lastName") String lastName, @RequestParam("firstName") String firstName) {
		try {
			saveFilesToServer(profileImage, firstName, lastName);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}

	public void saveFilesToServer(MultipartFile multipartFile, String firstName, String lastName) throws IOException, SQLException {
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		File file = null;
		String filePath;
		if(multipartFile.getSize() == 0) {
			User userProfileDetails = getUserProfile();
			filePath = userProfileDetails.getImagePath();
			file = new File("src/main/resources/static/" + filePath);
			
		}else {
			filePath = "upload/" + multipartFile.getOriginalFilename();
			file = new File("src/main/resources/static/" + filePath);
			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
			
		}
		userProfileService.updateUserProfileDetails(firstName, lastName, filePath, userName);
	}
}
