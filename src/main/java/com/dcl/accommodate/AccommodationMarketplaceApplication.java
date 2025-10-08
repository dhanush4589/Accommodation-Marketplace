package com.dcl.accommodate;

import User.UserRole;
import org.springframework.context.annotation.ComponentScan;
import security.jwt.JwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.Duration;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.dcl.accommodate",
        "security.jwt",
        "com.dcl.accommodate.config"
})

public class AccommodationMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccommodationMarketplaceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(JwtService jwtService) {
        return args -> {

            Map<String, Object> claims = Map.of(
                    "email", "abc@gmail.com",
                    "role", UserRole.HOST.name()
            );

            String token = jwtService.generateToken(claims, "sub:abc", Duration.ofHours(1));

            System.out.println("Generated JWT Token: " + token);
        };
    }
}

