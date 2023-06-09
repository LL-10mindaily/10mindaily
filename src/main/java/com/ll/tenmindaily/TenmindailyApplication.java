package com.ll.tenmindaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TenmindailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenmindailyApplication.class, args);
    }

}
