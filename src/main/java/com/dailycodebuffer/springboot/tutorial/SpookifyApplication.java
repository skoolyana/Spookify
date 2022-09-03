package com.dailycodebuffer.springboot.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpookifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpookifyApplication.class, args);
	}

}
