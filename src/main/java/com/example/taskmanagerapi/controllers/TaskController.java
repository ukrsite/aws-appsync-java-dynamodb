package com.example.taskmanagerapi.controllers;

import com.example.taskmanagerapi.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks() throws Exception {
        return taskService.listTasks();
    }

    @PostMapping
    public String createTask(@RequestParam String id, @RequestParam String title, @RequestParam boolean completed) throws Exception {
        return taskService.createTask(id, title, completed);
    }
}
