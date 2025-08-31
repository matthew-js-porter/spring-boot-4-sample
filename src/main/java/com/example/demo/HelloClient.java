package com.example.demo;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.registry.HttpServiceClient;

@HttpServiceClient("hello")
public interface HelloClient {

    @GetExchange(value = "/hello", version = "2")
    String hello();
}
