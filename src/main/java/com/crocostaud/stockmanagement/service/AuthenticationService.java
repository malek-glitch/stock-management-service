package com.crocostaud.stockmanagement.service;


import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.utils.security.Token;

public interface AuthenticationService {
    Token register(UserDto request);

    Token login(UserDto request);
}

