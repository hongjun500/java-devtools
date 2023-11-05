package com.hongjun;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class JpaGeneratorApp {

	public static void main(String[] args) {
		SpringApplication.run(JpaGeneratorApp.class, args);
	}
}