package com.backend.finalProject;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(FinalProjectApplication.class, args);
	}


}
