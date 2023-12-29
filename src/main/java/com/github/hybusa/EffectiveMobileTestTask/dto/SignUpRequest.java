package com.github.hybusa.EffectiveMobileTestTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Login is mandatory")
    String login;

    @NotBlank(message = "Password is mandatory")
    String password;
}