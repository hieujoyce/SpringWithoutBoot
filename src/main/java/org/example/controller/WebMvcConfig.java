package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Hieu Joyce check config");
        registry.addInterceptor(new LoggerInterceptor());
    }

//    @Bean
//    public MappedInterceptor myInterceptor() {
//        log.info("Hieu Joyce check config 1");
//        return new MappedInterceptor(null, new LoggerInterceptor());
//    }
}
