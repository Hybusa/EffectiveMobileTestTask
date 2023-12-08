package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.CommentDto;
import com.github.hybusa.EffectiveMobileTestTask.dto.PostCommentDto;
import com.github.hybusa.EffectiveMobileTestTask.servicies.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @Operation(
            summary = "Post comment", tags = "Comment",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Comment Not found", content = @Content)
            }
    )
    @PostMapping("{taskId}")
    public ResponseEntity<CommentDto> postComment(@Valid @RequestBody PostCommentDto postComment,
                                                  @PathVariable Long taskId) {
        Optional<CommentDto> commentOptional =  commentService.createComment(
                postComment,
                taskId,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return commentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
