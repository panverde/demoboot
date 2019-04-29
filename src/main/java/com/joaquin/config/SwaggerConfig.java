package com.joaquin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
	
	    final List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
	    responseMessageList
	        .add(new ResponseMessageBuilder().code(500).message("500 Internal Server Error").build());
	    responseMessageList.add(new ResponseMessageBuilder().code(403).message("Accessing the resource you were trying to reach is forbidden")
	    		.build());
	    responseMessageList.add(new ResponseMessageBuilder().code(401).message("You are not authorized to view the resource")
	    		.build());
	  
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.joaquin.controller"))
            .paths(PathSelectors.any())
            .build().useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, responseMessageList);
    
   
  }
  
}
