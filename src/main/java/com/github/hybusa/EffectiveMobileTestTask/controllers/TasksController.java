package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import com.github.hybusa.EffectiveMobileTestTask.servicies.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        return ResponseEntity.ok(taskService.createTask(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                task
        ));
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto task, @PathVariable Long id) {
        Optional<TaskDto> response = taskService.updateTask(task, id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("updateStatus/{id}")
    public ResponseEntity<TaskDto> updateTaskStatus(@RequestBody Status status, @PathVariable Long id) {
        Optional<TaskDto> taskDtoOptional = taskService.updateStatus(id, status);
        return taskDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDto>> getUserTasksWithCommentsByPage(@RequestParam("page") Integer pageNumber,
                                                                       @RequestParam("size") Integer pageSize,
                                                                       @PathVariable Long userId) {
        Optional<List<TaskDto>> tasksWrapperOptional = taskService.getUserTasksWithCommentsByPage(
                pageNumber,
                pageSize,
                userId
        );
        return tasksWrapperOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/assigned/{assignedId}")
    public ResponseEntity<List<TaskDto>> getAssignedTasksWithCommentsByPage(@RequestParam("page") Integer pageNumber,
                                                                           @RequestParam("size") Integer pageSize,
                                                                           @PathVariable Long assignedId) {
        Optional<List<TaskDto>> tasksWrapperOptional = taskService.getAssignedTasksWithCommentsByPage(
                pageNumber,
                pageSize,
                assignedId
        );
        return tasksWrapperOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
