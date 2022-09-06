package com.ssafy.uniqon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UniqonApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniqonApplication.class, args);
	}

}
