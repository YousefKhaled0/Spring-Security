package com.example.events;

import com.example.DTOs.UserDto;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {

    public RegistrationCompleteEvent(UserDto user) {
        super(user);
    }
}
