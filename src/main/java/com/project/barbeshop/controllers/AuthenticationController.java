package com.project.barbeshop.controllers;


import com.project.barbeshop.dto.*;
import com.project.barbeshop.entities.UserEntity;
import com.project.barbeshop.services.AuthenticationService;
import com.project.barbeshop.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService,
                                    AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(userMapper.toDto(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {


        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
