package com.crocostaud.stockmanagement.service;


import com.crocostaud.stockmanagement.dto.stock.UserDto;

public interface AuthenticationService {
    String signup(UserDto request);

    String signin(UserDto request);
}

