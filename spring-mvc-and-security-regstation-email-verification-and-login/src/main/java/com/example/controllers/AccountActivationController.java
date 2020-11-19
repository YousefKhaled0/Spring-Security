package com.example.controllers;

import com.example.services.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountActivationController {

    @Autowired
    private UserActivationService userActivationService;

    @GetMapping("/user/activate")
    public String activate(@RequestParam(name = "token") String token, ModelMap modelMap) {
        boolean b = userActivationService.activateAccount(token);
        if (b) {
            modelMap.addAttribute("message", "Your Account Has been Activated");
        } else {
            boolean b1 = userActivationService.generateNewToken(token);
            if (b1) {
                modelMap.addAttribute("message", "This link is expired a new link has been sent");
            } else {
                modelMap.addAttribute("message", "Wrong verification link");
            }
        }
        return "active";
    }
}
