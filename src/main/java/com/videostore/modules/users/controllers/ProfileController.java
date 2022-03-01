package com.videostore.modules.users.controllers;

import com.videostore.modules.users.dtos.CreateProfileDto;
import com.videostore.modules.users.dtos.ResponseProfileDto;
import com.videostore.modules.users.services.CreateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private CreateProfileService createProfileService;

    @PostMapping
    public ResponseEntity<ResponseProfileDto> create(@RequestBody @Valid CreateProfileDto createProfileDto){
        return  ResponseEntity.ok(createProfileService.execute(createProfileDto));
    }
}
