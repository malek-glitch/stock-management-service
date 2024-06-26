package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.AuthenticationService;
import com.crocostaud.stockmanagement.service.JwtService;
import com.crocostaud.stockmanagement.service.UserService;
import com.crocostaud.stockmanagement.utils.security.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserService userService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Token register(UserDto request) {
        UserDto user = userService.createUser(request, null);
        ShopUser userDetails = ShopUser.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(user.getRole())
                .shop(new Shop(user.getShopId()))
                .build();

        return new Token(jwtService.generateToken(userDetails), "TODO add refresh token");
    }

    @Override
    public Token login(UserDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = (ShopUser) userDetailsService.loadUserByUsername(request.getUsername());
        if (user == null)
            return null;

        Shop shop = null;
        if( user.getShop() != null)
            shop = user.getShop();

        ShopUser userDetails = ShopUser.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(user.getRole())
                .shop(shop)
                .build();
        return new Token(jwtService.generateToken(userDetails), "TODO add refresh token");
    }
}
