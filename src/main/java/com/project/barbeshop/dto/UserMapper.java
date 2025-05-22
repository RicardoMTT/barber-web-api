package com.project.barbeshop.dto;

import com.project.barbeshop.entities.RoleEntity;
import com.project.barbeshop.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList()));


        return dto;
    }

    public List<UserResponseDto> toDtoList(List<UserEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}