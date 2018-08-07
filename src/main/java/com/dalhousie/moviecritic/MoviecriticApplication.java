package com.dalhousie.moviecritic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.dalhousie")
public class MoviecriticApplication{

	public static void main(String[] args) {
		SpringApplication.run(MoviecriticApplication.class, args);

	}
	
}
