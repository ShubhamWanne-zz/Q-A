package com.example.QandAapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QandAApplication {

	public static void main(String[] args) {
		SpringApplication.run(QandAApplication.class, args);
	}
}
