package com.example;

import com.example.config.SendGridConfig;
import com.example.config.WebSecurityConfig;
import com.example.controllers.AccountActivationController;
import com.example.controllers.HelloController;
import com.example.controllers.LoginController;
import com.example.controllers.RegistrationController;
import com.example.listeners.RegistrationCompleteEventListener;
import com.example.services.CustomUserDetailService;
import com.example.services.UserActivationService;
import com.example.services.UserRegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({RegistrationController.class, WebSecurityConfig.class, LoginController.class,
        UserRegistrationService.class, RegistrationCompleteEventListener.class, CustomUserDetailService.class,
        HelloController.class, SendGridConfig.class, UserActivationService.class,
        AccountActivationController.class})
@EnableJpaRepositories(basePackages = "com.example.repos")
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
