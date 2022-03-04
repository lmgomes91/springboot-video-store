package com.videostore.modules.users.services;

import com.videostore.config.crypto.PasswordEncode;
import com.videostore.modules.users.dtos.CreateUserDto;
import com.videostore.modules.users.dtos.ResponseUserDto;
import com.videostore.modules.users.entities.Profile;
import com.videostore.modules.users.entities.User;
import com.videostore.modules.users.repositories.ProfileRepository;
import com.videostore.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PasswordEncode passwordEncode;

    public ResponseUserDto execute(CreateUserDto createUserDto) throws Exception {
        Optional<User> userExist = userRepository.findByEmail(createUserDto.getEmail());
        if(userExist.isPresent()){
            throw new Exception("E-mail already in use");
        }

        Optional<Profile> profile = profileRepository.findById(UUID.fromString(createUserDto.getProfileId()));
        if(profile.isEmpty()){
            throw new Exception("Profile not found");
        }

        String hashedPassword = passwordEncode.encoder(createUserDto.getPassword());

        User user = userRepository.save(new User(createUserDto.getName(), createUserDto.getEmail(), hashedPassword, profile.get()));

        return ResponseUserDto.convert(user);
    }
}
