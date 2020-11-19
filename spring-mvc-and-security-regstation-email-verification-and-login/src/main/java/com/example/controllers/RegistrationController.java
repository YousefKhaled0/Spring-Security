package com.example.controllers;

import com.example.DTOs.UserDto;
import com.example.events.RegistrationCompleteEvent;
import com.example.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/user/registration")
    public String showRegistrationForm(HttpServletRequest request, UserDto userDto, Model model) {
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUser(UserDto userDto, ModelMap model,
                               RedirectAttributes redirectAttributes) {
        if (userDto.getPassword().length() < 8) {
            model.addAttribute("user", userDto);
            model.addAttribute("error", "Weak password less than 8 characters");
            return "registration";
        }

        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            model.addAttribute("user", userDto);
            model.addAttribute("error", "Passwords don't match");
            return "registration";
        }

        if (userRegistrationService.register(userDto)) {
            eventPublisher.publishEvent(new RegistrationCompleteEvent(userDto));
            redirectAttributes.addFlashAttribute("message", "User Registration Completed successfully.");
            return "redirect:/user/login";
        }

        model.addAttribute("user", userDto);
        model.addAttribute("error", "This email already exists.");
        return "registration";
    }
}
