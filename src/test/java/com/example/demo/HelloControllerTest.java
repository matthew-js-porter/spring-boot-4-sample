package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.client.ApiVersionInserter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc)
                .apiVersionInserter(ApiVersionInserter.useQueryParam("version")).build();
    }

    @Test
    void saysHello() {
        restTestClient.get().uri("/hello").apiVersion("1")
                .exchange().expectBody(String.class).isEqualTo("Hello World");
    }

    @Test
    void saysHelloV2() throws Exception {
        mockMvc.perform(get("/hello").apiVersion("2").apiVersionInserter(ApiVersionInserter.useQueryParam("version")))
                .andExpect(status().isOk()).andExpect(content().string("Hello There!"));
    }

}