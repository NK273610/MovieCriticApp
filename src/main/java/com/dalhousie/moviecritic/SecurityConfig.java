package com.dalhousie.moviecritic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.dalhousie.moviecritic.service.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider customAuthProvider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		auth.authenticationProvider(customAuthProvider);
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/register.html")
                .antMatchers("/addUser")
                .antMatchers("/error.html")
                .antMatchers("/login")
                .antMatchers("/registrationconfig")
                .antMatchers("/forgotpass.html")
                .antMatchers("/forgotpassword");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests().anyRequest().hasRole("USER")
		.and().formLogin().loginPage("/login").permitAll()
		.loginProcessingUrl("/signin")
		.usernameParameter("username")
		.passwordParameter("password")
		.failureHandler(authenticationFailureHandler())
		.and()
		.logout()                                                              
		.logoutUrl("/logout")                                             
		.logoutSuccessUrl("/login") 
		.and().csrf().disable();
    }
	
	  @Bean
	  public AuthenticationFailureHandler authenticationFailureHandler()
	  {
	    return new CustomAuthenticationHandler();
	  }
}