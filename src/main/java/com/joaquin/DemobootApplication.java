package com.joaquin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DemobootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemobootApplication.class, args);
    }

}
