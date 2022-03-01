package com.videostore.modules.users.dtos;

import com.videostore.modules.users.entities.Profile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ResponseProfileDto {
    @NotNull
    @NotEmpty
    private UUID id;

    @NotNull
    @NotEmpty
    private String name;

    public ResponseProfileDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ResponseProfileDto convert(Profile profile){
        return new ResponseProfileDto(profile.getId(), profile.getName());
    }
}
