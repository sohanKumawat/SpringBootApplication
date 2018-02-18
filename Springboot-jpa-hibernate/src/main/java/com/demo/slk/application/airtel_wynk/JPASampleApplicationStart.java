package com.demo.slk.application.airtel_wynk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
public class JPASampleApplicationStart {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(JPASampleApplicationStart.class, args);
	}
}
