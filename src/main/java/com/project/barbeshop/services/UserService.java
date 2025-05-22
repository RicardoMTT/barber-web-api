package com.project.barbeshop.services;

import com.project.barbeshop.entities.UserEntity;
import com.project.barbeshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);  // Agregar cada elemento de iterable a la lista

        return users;
    }
}
