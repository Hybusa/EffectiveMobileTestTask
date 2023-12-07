package com.github.hybusa.EffectiveMobileTestTask.servicies;

import com.github.hybusa.EffectiveMobileTestTask.dto.PostTaskDto;
import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import com.github.hybusa.EffectiveMobileTestTask.exceptions.UserNotFoundException;
import com.github.hybusa.EffectiveMobileTestTask.mapper.TaskMapper;
import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import com.github.hybusa.EffectiveMobileTestTask.models.User;
import com.github.hybusa.EffectiveMobileTestTask.repositories.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public TaskDto createTask(String email, PostTaskDto task) {
        return taskMapper.taskToTaskDto(taskRepository.save(new Task(getUser(email), task)));
    }

    private User getUser(String email) {
        Optional<User> userOptional = userService.getUserByLogin(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return userOptional.get();
    }

    @PreAuthorize("hasRole('ADMIN') " +
            "OR authentication.name == @taskService.getTaskAuthorNameById(#id)")
    public Optional<TaskDto> updateTask(TaskDto taskDto, Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(task -> taskMapper.taskToTaskDto(
                taskRepository.save(taskMapper.taskDtoToTask(task, taskDto))));

    }
    @PreAuthorize("hasRole('ADMIN') " +
            "OR authentication.name == @taskService.getTaskAuthorNameById(#id)")
    public boolean deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }

    @PreAuthorize("hasRole('ADMIN') " +
            "OR authentication.name == @taskService.getTaskAssignedNameById(#id)")
    public Optional<TaskDto> updateStatus(Long id, Status status){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            return Optional.empty();
        }

        Task task = taskOptional.get();
        task.setStatus(status);
        return Optional.of(taskMapper.taskToTaskDto(task));
    }

    public Optional<List<TaskDto>> getUserTasksWithCommentsByPage(Integer pageNumber, Integer pageSize, Long authorId) {
        Optional<User> userOptional = userService.getUserById(authorId);
        if(userOptional.isEmpty()){
            return Optional.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);

        return Optional.of(taskMapper.listMap(taskRepository.findByAuthorId(authorId,pageRequest).getContent()));
    }

    public Optional<List<TaskDto>> getAssignedTasksWithCommentsByPage(Integer pageNumber, Integer pageSize, Long assignedId) {
        Optional<User> userOptional = userService.getUserById(assignedId);
        if(userOptional.isEmpty()){
            return Optional.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);

        return Optional.of(taskMapper.listMap(taskRepository.findByAssignedId(assignedId,pageRequest).getContent()));
    }


    public String getTaskAssignedNameById(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            return null;
        }
        User assigned = taskOptional.get().getAssigned();
        if(assigned == null){
            return null;
        }

        return assigned.getUsername();
    }


    public String getTaskAuthorNameById(Long id){
        return taskRepository.findById(id).map(t -> t.getAuthor().getLogin()).orElse("");
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }
}
