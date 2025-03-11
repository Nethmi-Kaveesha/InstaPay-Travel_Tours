package com.example.InstaPay_Travel_Tours;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InstaPayTravelToursApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaPayTravelToursApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}


