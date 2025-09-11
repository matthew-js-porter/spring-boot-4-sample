package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(HelloClientTest.class)
class HelloClientTest {

    @Autowired
    private HelloClient helloClient;

    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Test
    void hello() {
        mockRestServiceServer.expect(requestToUriTemplate("http://localhost:8080/hello?version={version}", 2))
                .andRespond(withSuccess("Hello There!", MediaType.TEXT_PLAIN));
        assertThat(helloClient.hello()).isEqualTo("Hello There!");
    }
}