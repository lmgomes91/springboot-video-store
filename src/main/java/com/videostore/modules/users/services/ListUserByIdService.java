package com.videostore.modules.users.services;

import com.videostore.modules.users.dtos.DetailedUserResponseDto;
import com.videostore.modules.users.entities.User;
import com.videostore.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ListUserByIdService {
    @Autowired
    private UserRepository userRepository;

    public DetailedUserResponseDto execute(UUID id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new Exception("User not found");
        }

        return DetailedUserResponseDto.convert(user.get());
    }
}
