package com.vegan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.vegan.repository")
public class VeganApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeganApplication.class, args);
	}

}
