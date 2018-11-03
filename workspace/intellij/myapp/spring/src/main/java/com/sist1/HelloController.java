package com.sist1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String sayHello() {
        return "<h2>Hello World !!!!!</h2>";
    }
}

