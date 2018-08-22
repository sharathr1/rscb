/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@ComponentScan(basePackages = "com.ge.dt.*")
@EnableJpaRepositories
@EnableCaching
//public class DigitalTwinApplication{
//
//	public static void main(String[] args) {
//		SpringApplication.run(DigitalTwinApplication.class, args);
//	}
//}
@SpringBootApplication
public class DigitalTwinApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DigitalTwinApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DigitalTwinApplication.class, args);
    }

}