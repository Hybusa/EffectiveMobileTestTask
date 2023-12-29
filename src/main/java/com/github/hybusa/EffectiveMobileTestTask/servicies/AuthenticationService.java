package com.github.hybusa.EffectiveMobileTestTask.servicies;
import com.github.hybusa.EffectiveMobileTestTask.dto.JwtAuthenticationResponse;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignInRequest;
import com.github.hybusa.EffectiveMobileTestTask.dto.SignUpRequest;
import com.github.hybusa.EffectiveMobileTestTask.enums.Role;
import com.github.hybusa.EffectiveMobileTestTask.exceptions.LoginAlreadyExists;
import com.github.hybusa.EffectiveMobileTestTask.models.User;
import com.github.hybusa.EffectiveMobileTestTask.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 UserService userService,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        if(userRepository.existsByLogin(request.getLogin())){
            throw new LoginAlreadyExists("There is a user with this Login");
        }
        User user = User
                .builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}