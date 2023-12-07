package com.github.hybusa.EffectiveMobileTestTask.servicies;

import com.github.hybusa.EffectiveMobileTestTask.dto.PostCommentDto;
import com.github.hybusa.EffectiveMobileTestTask.exceptions.UserNotFoundException;
import com.github.hybusa.EffectiveMobileTestTask.models.Comment;
import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import com.github.hybusa.EffectiveMobileTestTask.models.User;
import com.github.hybusa.EffectiveMobileTestTask.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;
    private final UserService userService;
    public CommentService(CommentRepository commentRepository, TaskService taskService, UserService userService) {
        this.commentRepository = commentRepository;
        this.taskService = taskService;
        this.userService = userService;
    }

    public Optional<Comment> createComment(PostCommentDto postComment, Long taskId, String name) {
        Optional<Task>  taskOptional = taskService.getTaskById(taskId);
        if(taskOptional.isEmpty()){
            return Optional.empty();
        }

        Optional<User> userOptional = userService.getUserByLogin(name);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("user not found comment Exception");
        }
        return Optional.of(commentRepository.save(new Comment(postComment, taskOptional.get(), userOptional.get())));
    }
}
