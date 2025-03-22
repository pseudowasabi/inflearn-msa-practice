package com.pseudowasasbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    /*
    - way to run program in command prompt: [mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=9003']
    - if server.port=0 (random port), just run with: [mvn spring-boot:run]
     */
}
