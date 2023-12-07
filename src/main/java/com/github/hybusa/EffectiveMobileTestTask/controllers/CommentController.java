package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.PostCommentDto;
import com.github.hybusa.EffectiveMobileTestTask.models.Comment;
import com.github.hybusa.EffectiveMobileTestTask.servicies.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{taskId}")
    public ResponseEntity<Comment> postComment(@Valid @RequestBody PostCommentDto postComment,
                                                  @PathVariable Long taskId) {
        Optional<Comment> commentOptional =  commentService.createComment(
                postComment,
                taskId,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return commentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
