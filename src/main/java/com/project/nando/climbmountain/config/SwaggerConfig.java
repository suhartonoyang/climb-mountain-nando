package com.project.nando.climbmountain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
    public Docket api() { 
		
		String title = "API Project Climb Mountain";
		String description = "Api documentation for project climb mountain";
		String version = "1.0";
		ApiInfo apiInfo = new ApiInfo(title, description, version, null, null, null, null);
		
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo)
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}
