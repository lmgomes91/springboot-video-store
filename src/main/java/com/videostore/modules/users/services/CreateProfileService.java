package com.videostore.modules.users.services;

import com.videostore.modules.users.dtos.CreateProfileDto;
import com.videostore.modules.users.dtos.ResponseProfileDto;
import com.videostore.modules.users.entities.Profile;
import com.videostore.modules.users.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ResponseProfileDto execute(CreateProfileDto createProfileDto){
        return ResponseProfileDto.convert(profileRepository.save(new Profile(createProfileDto.getName())));
    }
}
