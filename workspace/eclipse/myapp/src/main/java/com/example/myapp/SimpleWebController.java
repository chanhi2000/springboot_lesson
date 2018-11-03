package com.example.myapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SimpleWebController {
	@RequestMapping("/")
	public String greetings() {
		return "<h2>Hello Spring Boot!!</h2>";
	}
}
