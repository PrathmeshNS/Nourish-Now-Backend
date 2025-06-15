package com.pscode.nourish_now;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@Configuration
public class NourishNowApplication {

    public static void main(String[] args) {
        SpringApplication.run(NourishNowApplication.class, args);
    }

}
