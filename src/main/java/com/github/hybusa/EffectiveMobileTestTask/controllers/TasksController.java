package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.servicies.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok(taskService.createTask(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                task
        ));
    }
}
