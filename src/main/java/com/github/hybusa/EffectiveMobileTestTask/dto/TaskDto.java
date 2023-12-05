package com.github.hybusa.EffectiveMobileTestTask.dto;

import com.github.hybusa.EffectiveMobileTestTask.enums.Priority;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TaskDto {

    String title;

    String description;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    Priority priority;
}
