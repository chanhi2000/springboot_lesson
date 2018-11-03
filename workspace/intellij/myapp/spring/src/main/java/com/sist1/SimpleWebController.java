package com.sist1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleWebController {
    @RequestMapping
    public String greetings() {
        return "<h2>Hello Spring Boot !!!</h2>";
    }
}
