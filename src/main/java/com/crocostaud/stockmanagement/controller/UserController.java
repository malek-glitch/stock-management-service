package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.annotation.User;
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


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user, @User ShopUser authUser) {
        if (authUser == null || authUser.getShop() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");

        try {
            UserDto savedUser = userService.createUser(user, authUser.getShop().getId());
            return ResponseEntity.ok().body(savedUser);
        } catch (RuntimeException ignore) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId, @User ShopUser user) {
        long id = user.getId();

        if (userId != id) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User id not match");
        }

        UserDto userDto = userService.getUserById(userId);
        if (userDto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @User ShopUser authUser) {
        if (authUser == null)
            return ResponseEntity.noContent().build();
        long id = authUser.getId();
        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User id not match");
        }
        UserDto updatedUser = userService.updateUser(user, id);
        if (updatedUser == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(updatedUser);
    }

}
