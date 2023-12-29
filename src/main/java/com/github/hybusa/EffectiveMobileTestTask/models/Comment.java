package com.github.hybusa.EffectiveMobileTestTask.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.hybusa.EffectiveMobileTestTask.dto.PostCommentDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "comments")
public class Comment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    public Comment(PostCommentDto commentDto, Task task, User user){
        this.text = commentDto.getText();
        this.author = user;
        this.task = task;
    }

}
