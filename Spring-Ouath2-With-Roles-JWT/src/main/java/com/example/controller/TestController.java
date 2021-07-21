package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

	@GetMapping("/admin")
	public String helloAdmin(Principal principal) {
		return String.join(" ", "hello", principal.getName());
	}

	@GetMapping("/user")
	public String helloUser(Principal principal) {
		return String.join(" ", "hello", principal.getName());
	}

}
