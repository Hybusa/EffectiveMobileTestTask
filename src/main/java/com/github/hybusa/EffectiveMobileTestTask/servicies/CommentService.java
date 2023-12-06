package com.github.hybusa.EffectiveMobileTestTask.servicies;

import com.github.hybusa.EffectiveMobileTestTask.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;

    public CommentService(CommentRepository commentRepository, TaskService taskService) {
        this.commentRepository = commentRepository;
        this.taskService = taskService;
    }


}
