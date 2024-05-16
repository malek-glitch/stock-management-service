package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.UserDto;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.annotation.Username;
import com.crocostaud.stockmanagement.utils.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Auth auth;

    @Autowired
    public UserController(UserService userService, Auth auth) {
        this.userService = userService;
        this.auth = auth;
    }


    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        System.out.println(user);
        UserDto savedUser = null;
        try {
            savedUser = userService.createUser(user);
        } catch (RuntimeException e) {
            if (e.getCause().getMessage().contains("Duplicate entry")) {
                throw e;
            }
        }
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId, @Username String username) {
        long id = auth.getUser(username) == null ? -1 : auth.getUser(username).getId();

        if (userId != id) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User id not match");
        }

        UserDto user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(user);
    }

}
