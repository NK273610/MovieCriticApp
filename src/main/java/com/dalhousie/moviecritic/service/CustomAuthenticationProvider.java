package com.dalhousie.moviecritic.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.dalhousie.moviecritic.dao.IBusinessLogicDAO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
	@Autowired
	IUserService userService;
	
	@Autowired
	IBusinessLogicDAO dao;
	
	@Override
    public Authentication authenticate(Authentication auth) 
      throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        boolean isValid = false;
		try {
			try {
				isValid = userService.validateUser(username, password);
			} catch (SQLException e) {
				throw new
				AuthenticationServiceException("SQL exception while validating user login");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new
			AuthenticationServiceException("Exception with hashing password");
		}
		 catch (IllegalArgumentException e) {
				throw new
				AuthenticationServiceException("Exception with hashing password");
			}
        if (isValid) {
        	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication authentication = new UsernamePasswordAuthenticationToken
              (username, password, grantedAuthorities);
            return authentication;
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
        }
    }
 
    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
