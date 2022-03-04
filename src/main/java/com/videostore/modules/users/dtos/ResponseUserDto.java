package com.videostore.modules.users.dtos;

import com.videostore.modules.users.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ResponseUserDto {
    @NotNull
    @NotEmpty
    private UUID id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    public ResponseUserDto(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static ResponseUserDto convert(User user){
        return new ResponseUserDto(user.getId(), user.getName(), user.getEmail());
    }
}
