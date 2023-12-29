package com.github.hybusa.EffectiveMobileTestTask.controllers;

import com.github.hybusa.EffectiveMobileTestTask.dto.JwtAuthenticationResponse;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignInRequest;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignUpRequest;
import com.github.hybusa.EffectiveMobileTestTask.servicies.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(
            summary = "Sign up", tags = "User",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Already exists", content = @Content)
            }
    )
    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(
            summary = "Login", tags = "User",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "*/*",
                                    schema = @Schema(implementation = Collection.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PostMapping("/signin")
    public JwtAuthenticationResponse signIn(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }


}