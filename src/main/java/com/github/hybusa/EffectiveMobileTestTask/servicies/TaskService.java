package com.github.hybusa.EffectiveMobileTestTask.servicies;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.mapper.TaskMapper;
import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import com.github.hybusa.EffectiveMobileTestTask.models.User;
import com.github.hybusa.EffectiveMobileTestTask.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserService userService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userService = userService;
    }

    public TaskDto createTask(String email, TaskDto task) {
        Optional<User> userOptional = userService.getUserByLogin(email);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        return taskMapper.taskToTaskDto(taskRepository.save(new Task(userOptional.get(), task)));
    }
}
