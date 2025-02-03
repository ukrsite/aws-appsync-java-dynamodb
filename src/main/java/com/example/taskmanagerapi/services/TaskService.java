package com.example.taskmanagerapi.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {

    private final AppSyncClient appSyncClient;
    private final DynamoDBService dynamoDBService;

    public TaskService(AppSyncClient appSyncClient, DynamoDBService dynamoDBService) {
        this.appSyncClient = appSyncClient;
        this.dynamoDBService = dynamoDBService;
    }

    public String listTasks() throws Exception {
        String query = """
            query ListTasks {
              listTasks {
                id
                title
                completed
              }
            }
        """;
        return appSyncClient.executeGraphQLQuery(query, new HashMap<>());
    }

    public String createTask(String id, String title, boolean completed) throws Exception {
        // dynamoDBService.createTask(id, title, completed);

        String mutation = """
            mutation CreateTask($id: ID!, $title: String!, $completed: Boolean!) {
              createTask(id: $id, title: $title, completed: $completed) {
                id
                title
                completed
              }
            }
        """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);
        variables.put("title", title);
        variables.put("completed", completed);

        return appSyncClient.executeGraphQLQuery(mutation, variables);
    }
}