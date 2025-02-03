package com.example.taskmanagerapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AppSyncClient {

    private static final Logger logger = LoggerFactory.getLogger(AppSyncClient.class);

    @Value("${aws.appsync.api.url}")
    private String apiUrl;

    @Value("${aws.appsync.api.key}")
    private String apiKey;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AppSyncClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Executes a GraphQL query against the AppSync API.
     *
     * @param query The GraphQL query string.
     * @param variables The variables for the GraphQL query.
     * @return The response body as a String.
     * @throws Exception if the request fails or the response is not as expected.
     */
    public String executeGraphQLQuery(String query, Map<String, Object> variables) throws Exception {
        // Construct the request body
        Map<String, Object> requestBody = Map.of("query", query, "variables", variables);
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Log the request (except API key for security)
        logger.info("Sending GraphQL query to: {}", apiUrl);
        logger.debug("Request body: {}", jsonBody);

        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Log the response (this can be adjusted based on the content of the response)
        logger.debug("Response received: {}", response.body());

        // Handle non-200 status codes
        if (response.statusCode() != 200) {
            logger.error("Error in GraphQL query execution. Status code: {}. Response: {}", response.statusCode(), response.body());
            throw new RuntimeException("Error in GraphQL query execution: " + response.body());
        }

        // Return the response body
        return response.body();
    }


}
