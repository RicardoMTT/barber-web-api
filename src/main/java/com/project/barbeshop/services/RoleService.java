package com.project.barbeshop.services;

import com.project.barbeshop.dto.RoleEnum;
import com.project.barbeshop.entities.RoleEntity;
import com.project.barbeshop.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Este método se ejecuta después de que el servicio sea inicializado
    @PostConstruct
    public void init() {
        // Inicializar roles predeterminados si no existen
        Arrays.stream(RoleEnum.values()).forEach(roleEnum -> {
            roleRepository.findByName(roleEnum.name())
                    .orElseGet(() -> roleRepository.save(new RoleEntity(roleEnum.name())));
        });
    }

    public RoleEntity findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }

}
