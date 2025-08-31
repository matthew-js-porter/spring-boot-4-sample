package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello", version = "1")
    String hello() {
        return "Hello World";
    }

    @GetMapping(value = "/hello", version = "2")
    String hello2() {
        return "Hello There!";
    }
}
