package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/user/login")
    public String showRegistrationForm(ModelMap modelMap) {
        return "login";
    }
}
