package com.dalhousie.moviecritic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("restAuthenticationEntryPoint")
public class CustomAuthenticationHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(exception instanceof BadCredentialsException) {
			response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.sendRedirect(request.getContextPath() + "/login?error");
		}else {
			response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	        response.sendRedirect("/error.html");
		}
		

	}

}
