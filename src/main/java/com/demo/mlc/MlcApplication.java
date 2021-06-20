package com.demo.mlc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication /*(exclude = {SecurityAutoConfiguration.class})*/
public class MlcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlcApplication.class, args);
	}
}
