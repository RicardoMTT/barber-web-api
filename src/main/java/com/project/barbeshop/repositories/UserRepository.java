package com.project.barbeshop.repositories;

import com.project.barbeshop.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

}
