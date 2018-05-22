package com.ge.dt.digitaltwin;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAspectJAutoProxy
@EnableSwagger2
public class AppConfig {
	
	 @Bean
	 public DozerBeanMapper getMapper() {
	        return new DozerBeanMapper();
	 }

	 @Bean 
	 public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){ 
		 return new PropertySourcesPlaceholderConfigurer(); 
	 }
	 
	  @Bean
	    public Docket productApi() {
		  return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select().apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any()).build();
	    }
	 
}