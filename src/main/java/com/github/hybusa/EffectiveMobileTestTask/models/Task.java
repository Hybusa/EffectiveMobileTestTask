package com.github.hybusa.EffectiveMobileTestTask.models;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Priority;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "assigned_id")
    private User assigned;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Collection<Comment> comments;

    public Task(User user, TaskDto taskDto) {
        this.author = user;
        this.title = taskDto.getTitle();
        this.description = taskDto.getDescription();
        this.priority = taskDto.getPriority();
        this.status = taskDto.getStatus();
    }
}
