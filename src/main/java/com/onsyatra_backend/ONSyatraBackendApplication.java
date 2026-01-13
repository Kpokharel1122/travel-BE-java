package com.onsyatra_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.onsyatra_backend", exclude = {DataSourceAutoConfiguration.class} )
public class ONSyatraBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ONSyatraBackendApplication.class, args);
	}

}
