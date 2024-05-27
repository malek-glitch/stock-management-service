package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.annotation.Username;
import com.crocostaud.stockmanagement.utils.security.Auth;
import com.crocostaud.stockmanagement.utils.security.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {

    private final Auth auth;
    private final UserService userService;

    public Controller(Auth auth, UserService userService) {
        this.auth = auth;
        this.userService = userService;
    }


    // 53 044 193 m.Manel
//    @GetMapping
    private ResponseEntity<UserDto> testMethod(@Username String username) {
        System.out.println(username);
        ShopUser user = auth.getUser(username);
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .shopId(user.getShop().getId())
                .build();
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody UserDto userDto) {
        System.out.println("________________________________________________");
        System.out.println((userDto.getUsername() + " " + userDto.getPassword()));
        return ResponseEntity.ok(userService.login(userDto));
    }

    @GetMapping("/home")
    private ResponseEntity<String> home() {
        return ResponseEntity.ok("hello home!");
    }

    @GetMapping("/dashboard")
    private ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("hello dashboard!");
    }

}
