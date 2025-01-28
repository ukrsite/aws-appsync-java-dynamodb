package com.example.taskmanagerapi.services;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DynamoDBService {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "TaskTable";

    public DynamoDBService() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of("us-east-1"))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void createTask(String id, String title, boolean completed) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(Map.of(
                        "id", AttributeValue.builder().s(id).build(),
                        "title", AttributeValue.builder().s(title).build(),
                        "completed", AttributeValue.builder().bool(completed).build()
                ))
                .build();
        dynamoDbClient.putItem(request);
    }

    public Map<String, AttributeValue> getTask(String id) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();
        return dynamoDbClient.getItem(request).item();
    }
}
