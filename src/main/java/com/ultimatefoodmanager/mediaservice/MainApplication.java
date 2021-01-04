package com.ultimatefoodmanager.mediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.ultimatefoodmanager.mediaservice")
@EnableFeignClients(basePackages = "com.ultimatefoodmanager.mediaservice.feign")
public class MainApplication {
	public static void main(String... args) {
		SpringApplication.run(MainApplication.class, args);
	}
}