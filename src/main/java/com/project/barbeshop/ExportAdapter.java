package com.project.barbeshop;

import com.project.barbeshop.entities.UserEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ExportAdapter {

    private  UserExport userExport;

    public ExportAdapter(UserExport userExport) {
        this.userExport = userExport;
    }

    public ByteArrayInputStream execute(List<UserEntity> users) {
        return userExport.execute(users);
    }
}
