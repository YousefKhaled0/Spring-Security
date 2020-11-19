package com.example.listeners;

import com.example.DTOs.UserDto;
import com.example.events.RegistrationCompleteEvent;
import com.example.services.UserActivationService;
import com.example.services.UserRegistrationService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserActivationService userActivationService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent registrationCompleteEvent) {
        String token = userRegistrationService.generateToken((UserDto) registrationCompleteEvent.getSource());
        try {
            Response response = userActivationService.sendActivationEmail((UserDto) registrationCompleteEvent.getSource(), token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
