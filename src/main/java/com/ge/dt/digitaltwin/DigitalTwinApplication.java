package com.ge.dt.digitaltwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ge.dt.*")
@EnableJpaRepositories
public class DigitalTwinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalTwinApplication.class, args);
	}
}
