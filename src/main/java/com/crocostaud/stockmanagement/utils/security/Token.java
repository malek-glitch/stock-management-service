package com.crocostaud.stockmanagement.utils.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    private String token;
    private String refresh_token;
}
