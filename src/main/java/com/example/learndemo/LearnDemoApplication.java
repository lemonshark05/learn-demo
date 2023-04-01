package com.example.learndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class LearnDemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LearnDemoApplication.class);
		ConfigurableEnvironment env = new StandardEnvironment();
		app.setEnvironment(env);
		app.run(args);
		System.out.println("Server Port: " + env.getProperty("server.port"));
//		SpringApplication.run(LearnDemoApplication.class, args);
	}

}
