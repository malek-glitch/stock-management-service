package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.UserDto;
import com.crocostaud.stockmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    ResponseEntity<UserDto> getAllUsers() {
        return ResponseEntity.ok(
                UserDto.builder()
                        .username("malek")
                        .email("malekgharyani19@gmail.com")
                        .password("password")
                        .id(3L)
                        .build()
        );
    }

}
