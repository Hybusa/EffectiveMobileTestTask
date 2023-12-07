package com.github.hybusa.EffectiveMobileTestTask.dto;

import com.github.hybusa.EffectiveMobileTestTask.enums.Priority;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String author;
    private String assigned;

    private Collection<CommentDto> comments;
}
