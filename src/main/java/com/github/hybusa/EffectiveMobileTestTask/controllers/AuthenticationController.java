package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.JwtAuthenticationResponse;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignInRequest;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignUpRequest;
import com.github.hybusa.EffectiveMobileTestTask.servicies.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signIn(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }


}