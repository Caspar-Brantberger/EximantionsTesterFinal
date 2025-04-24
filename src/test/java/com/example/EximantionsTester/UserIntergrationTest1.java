package com.example.EximantionsTester;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntergrationTest1 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetUserByHttp(){
        User user = new User(null,"John","@Doe");

        ResponseEntity<User> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/user", user, User.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Long userId = postResponse.getBody().getId();

        ResponseEntity <User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/user/" + userId, User.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("John", getResponse.getBody().getName());
        assertEquals("@Doe", getResponse.getBody().getEmail());


    }
}
