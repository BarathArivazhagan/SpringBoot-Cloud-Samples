package com.barath.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClientDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientDemoAppApplication.class, args);
	}
	
	@GetMapping("/")
	public String home(){
		return "welcome to eureka client";
	}
}
