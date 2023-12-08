package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.PostTaskDto;
import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import com.github.hybusa.EffectiveMobileTestTask.servicies.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Create a task", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody PostTaskDto task) {
        return ResponseEntity.ok(taskService.createTask(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                task
        ));
    }

    @Operation(
            summary = "Update a task", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto task, @PathVariable Long id) {
        Optional<TaskDto> response = taskService.updateTask(task, id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Delete a task", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad request, no such task"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Update task status", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad request, no such task"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("updateStatus/{id}")
    public ResponseEntity<TaskDto> updateTaskStatus(@Valid @RequestBody Status status, @PathVariable Long id) {
        Optional<TaskDto> taskDtoOptional = taskService.updateStatus(id, status);
        return taskDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get user tasks", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad request, no such task"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
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

    @Operation(
            summary = "Get tasks by assigned user", tags = "Task",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
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
