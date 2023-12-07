package com.github.hybusa.EffectiveMobileTestTask.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    String login;
    String password;
}