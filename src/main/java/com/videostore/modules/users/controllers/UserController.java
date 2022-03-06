package com.videostore.modules.users.controllers;

import com.videostore.modules.users.dtos.CreateUserDto;
import com.videostore.modules.users.dtos.DetailedUserResponseDto;
import com.videostore.modules.users.dtos.ResponseUserDto;
import com.videostore.modules.users.services.CreateUserService;
import com.videostore.modules.users.services.ListUserByIdService;
import com.videostore.modules.users.services.ListUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private ListUsersService listUsersService;
    @Autowired
    private ListUserByIdService listUserByIdService;

    @PostMapping
    public ResponseEntity<ResponseUserDto> create(@RequestBody @Valid CreateUserDto createUserDto){
        try {
            return ResponseEntity.ok(createUserService.execute(createUserDto));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public Page<ResponseUserDto> list(@RequestParam(required = false) String name,
                                      @PageableDefault(sort = "id",
                                              direction = Sort.Direction.DESC,
                                              page = 0, size = 5) Pageable pagination){
        return listUsersService.execute(name, pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserResponseDto> listById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(listUserByIdService.execute(id));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().build();
        }
    }
}
