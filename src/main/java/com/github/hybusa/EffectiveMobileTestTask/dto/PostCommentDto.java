package com.github.hybusa.EffectiveMobileTestTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class PostCommentDto {

    @NotBlank(message = "Text is mandatory")
    String text;
}
