package com.videostore.modules.users.dtos;

import com.videostore.modules.users.entities.Profile;
import com.videostore.modules.users.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class DetailedUserResponseDto {
    @NotNull
    @NotEmpty
    private UUID id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private List<Profile> profiles;

    public DetailedUserResponseDto(UUID id, String name, String email, List<Profile> profiles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profiles = profiles;
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

    public List<Profile> getProfiles() {
        return profiles;
    }

    public static DetailedUserResponseDto convert(User user){
        return new DetailedUserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getProfiles());
    }
}
