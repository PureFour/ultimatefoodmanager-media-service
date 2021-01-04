package com.purefour.mediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.purefour.mediaservice")
public class MainApplication {
	public static void main(String... args) {
		SpringApplication.run(MainApplication.class, args);
	}
}