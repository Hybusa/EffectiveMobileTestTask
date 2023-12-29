package com.github.hybusa.EffectiveMobileTestTask.models;

import com.github.hybusa.EffectiveMobileTestTask.dto.PostTaskDto;
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

    public Task(User user, PostTaskDto postTaskDto) {
        this.author = user;
        this.title = postTaskDto.getTitle();
        this.description = postTaskDto.getDescription();
        this.priority = postTaskDto.getPriority();
        this.status = postTaskDto.getStatus();
    }
}
