package com.nrx.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BasicauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicauthApplication.class, args);
	}

}
