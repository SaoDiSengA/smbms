package com.tao.config;


import com.tao.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.tao.pojo")
public class Config {

    @Bean
    public User getUser(){
        return new User();
    }
}
