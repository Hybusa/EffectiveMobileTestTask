package com.github.hybusa.EffectiveMobileTestTask.models;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Priority;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    Priority priority;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    User author;

    @ManyToOne
    @JoinColumn(name = "assigned_id", nullable = false)
    User assigned;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    List<Comment> comments;

    public Task(User user, TaskDto taskDto) {
        this.author = user;
        this.title = taskDto.getTitle();
        this.description = taskDto.getDescription();
        this.priority = taskDto.getPriority();
        this.status = taskDto.getStatus();
    }
}
