package com.technicaltest.test_backed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestBackedApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TestBackedApplication.class, args);
	}

}
