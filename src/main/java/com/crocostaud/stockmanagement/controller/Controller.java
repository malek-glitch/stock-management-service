package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.service.AuthenticationService;
import com.crocostaud.stockmanagement.utils.security.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {

    private final AuthenticationService authenticationService;

    public Controller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // 53 044 193 m.Manel

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.login(userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

    @GetMapping("/")
    private ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("hello dashboard!");
    }

}
