package com.github.hybusa.EffectiveMobileTestTask.dto;

import com.github.hybusa.EffectiveMobileTestTask.enums.Priority;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class PostTaskDto {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
