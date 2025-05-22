package com.project.barbeshop;

import com.project.barbeshop.entities.UserEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface UserExport {
    ByteArrayInputStream execute(List<UserEntity> users);

}
