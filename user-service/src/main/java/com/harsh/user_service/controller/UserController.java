package com.harsh.user_service.controller;

import com.harsh.user_service.dto.UserDto;
import com.harsh.user_service.entity.User;
import com.harsh.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDto dto) {
        return service.register(dto);
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return service.getByEmail(email);
    }
}
