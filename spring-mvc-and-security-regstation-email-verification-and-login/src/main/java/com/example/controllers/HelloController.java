package com.example.controllers;

import com.example.DTOs.UserDto;
import com.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HelloController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/hello")
    public String hello(Principal principal , ModelMap modelMap)
    {
        UserDto byEmail = userRepo.findByEmail(principal.getName());
        if (byEmail.isVerified()) {
            modelMap.addAttribute("message", "Hello "
                    + byEmail.getFirstName() + " "
                    + byEmail.getLastName() + " your Account is Verified");
        }
        else
        modelMap.addAttribute("message", "Hello "
                + byEmail.getFirstName() + " "
                + byEmail.getLastName() + " your Account isn't Verified");

        return "home";
    }

}
