package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testHello() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/",
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!", response.getBody());
    }

    @Test
    public void testCalc() {
        ResponseEntity<HelloController.Result> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calc?left=100&right=200",
                HelloController.Result.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(100, response.getBody().getLeft());
        assertEquals(200, response.getBody().getRight());
        assertEquals(300, response.getBody().getAnswer());
    }

    @Test
    public void testCalcWithDifferentValues() {
        ResponseEntity<HelloController.Result> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/calc?left=50&right=75",
                HelloController.Result.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getLeft());
        assertEquals(75, response.getBody().getRight());
        assertEquals(125, response.getBody().getAnswer());
    }
}