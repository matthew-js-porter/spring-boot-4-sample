package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HelloClientTest {

    @Autowired
    private HelloClient helloClient;

    @Test
    void hello() {
        assertThat(helloClient.hello()).isEqualTo("Hello There!");
    }

}