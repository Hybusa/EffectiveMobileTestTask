package com.github.hybusa.EffectiveMobileTestTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotBlank(message = "Login is mandatory")
    String login;


    @NotBlank(message = "Password is mandatory")
    String password;
}
