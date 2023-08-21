package com.stardrinks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.stardrinks")
public class AppConfig {

    @Bean
    public Shop shop() {
        return new Shop("src/resources/drinks.csv", "src/resources/beans.csv", "src/resources/goodies.csv");
    }
}
