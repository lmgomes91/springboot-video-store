package com.videostore.modules.users.services;

import com.videostore.modules.users.dtos.ResponseProfileDto;
import com.videostore.modules.users.entities.Profile;
import com.videostore.modules.users.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListProfilesService {
    @Autowired
    private ProfileRepository profileRepository;

    public Page<ResponseProfileDto> execute(String name, Pageable pagination){
        Page<Profile> profiles;
        if(name != null){
            profiles = this.profileRepository.findByName(name, pagination);
        } else {
            profiles = this.profileRepository.findAll(pagination);
        }

        return profiles.map(ResponseProfileDto::convert);

    }
}
