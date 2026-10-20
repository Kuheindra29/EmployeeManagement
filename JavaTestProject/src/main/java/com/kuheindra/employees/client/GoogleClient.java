package com.kuheindra.employees.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GoogleClient {

    private final RestTemplate restTemplate;

    public String pingGoogle() {

        return restTemplate.getForObject(
                "https://www.google.com",
                String.class
        );
    }
}