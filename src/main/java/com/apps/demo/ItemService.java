package com.apps.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ItemService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ItemService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<Item> getItems() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Item[] items = objectMapper.readValue(response.getBody(), Item[].class);
                return Arrays.asList(items);
            } else {
                System.err.println("Failed to retrieve items. Status code: " + response.getStatusCode());
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            System.err.println("Error calling the API: " + e.getMessage());
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
        }


        return Collections.emptyList();
    }
}
