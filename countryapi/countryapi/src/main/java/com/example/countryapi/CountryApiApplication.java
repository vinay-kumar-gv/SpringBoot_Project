package com.example.countryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class
CountryApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CountryApiApplication.class, args);
	}
}


