package com.project.barbeshop.services;

import com.project.barbeshop.exception.EmailAlreadyExistsException;
import com.project.barbeshop.exception.InvalidCredentialsException;
import com.project.barbeshop.dto.RoleEnum;
import com.project.barbeshop.dto.RegisterUserDto;
import com.project.barbeshop.dto.LoginUserDto;
import com.project.barbeshop.entities.RoleEntity;
import com.project.barbeshop.entities.UserEntity;
import com.project.barbeshop.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;


    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleService roleService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Transactional
    public UserEntity signup(RegisterUserDto input) {

        // Verificar si el email ya existe
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(input.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        // Asignar rol de usuario por defecto
        RoleEntity userRole = roleService.findByName(RoleEnum.USER.name());
        user.addRole(userRole);


        return userRepository.save(user);
    }

    public UserEntity authenticate(LoginUserDto input) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );


        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
