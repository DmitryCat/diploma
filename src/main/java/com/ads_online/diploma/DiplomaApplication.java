package com.ads_online.diploma;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
public class DiplomaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiplomaApplication.class, args);
	}

}
