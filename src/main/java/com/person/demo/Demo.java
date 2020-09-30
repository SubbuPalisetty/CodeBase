package com.person.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.person.*")
public class Demo {

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

}
