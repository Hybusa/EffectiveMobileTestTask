package com.github.hybusa.EffectiveMobileTestTask.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CommentDto {

    private Long id;
    private String text;
    private String author;
}
