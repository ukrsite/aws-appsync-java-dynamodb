package com.example.taskmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.taskmanagerapi")
public class TaskManagerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerapiApplication.class, args);
	}
}
