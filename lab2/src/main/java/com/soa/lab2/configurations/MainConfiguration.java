package com.soa.lab2.configurations;

import com.soa.lab2.beans.City;
import com.soa.lab2.beans.Coordinates;
import com.soa.lab2.beans.Human;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MainConfiguration{

    @Bean()
    @Scope("prototype")
    public City city(){
        return new City();
    }

    @Bean()
    @Scope("prototype")
    public Human human(){
        return new Human();
    }

    @Bean()
    @Scope("prototype")
    public Coordinates coordinates(){
        return new Coordinates();
    }
}
