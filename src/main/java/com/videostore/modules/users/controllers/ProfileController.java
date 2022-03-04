package com.videostore.modules.users.controllers;

import com.videostore.modules.users.dtos.CreateProfileDto;
import com.videostore.modules.users.dtos.ResponseProfileDto;
import com.videostore.modules.users.services.CreateProfileService;
import com.videostore.modules.users.services.ListProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private CreateProfileService createProfileService;
    @Autowired
    private ListProfilesService listProfilesService;

    @PostMapping
    public ResponseEntity<ResponseProfileDto> create(@RequestBody @Valid CreateProfileDto createProfileDto){
        return  ResponseEntity.ok(createProfileService.execute(createProfileDto));
    }

    @GetMapping
    public Page<ResponseProfileDto> list (@RequestParam(required = false) String name,
                                          @PageableDefault(sort = "id",
                                                            direction = Sort.Direction.DESC,
                                                            page = 0, size = 5) Pageable pagination){
        return listProfilesService.execute(name, pagination);
    }
}
