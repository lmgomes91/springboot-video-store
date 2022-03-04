package com.videostore.modules.users.services;

import com.videostore.config.crypto.PasswordEncode;
import com.videostore.modules.users.dtos.ResponseUserDto;
import com.videostore.modules.users.entities.User;
import com.videostore.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListUsersService {
    @Autowired
    private UserRepository userRepository;


    public Page<ResponseUserDto> execute(String name, Pageable pagination){
        Page<User> users;

        if(name != null){
            users = userRepository.findByName(name, pagination);
        } else {
            users = userRepository.findAll(pagination);
        }

        return users.map(ResponseUserDto::convert);
    }
}
