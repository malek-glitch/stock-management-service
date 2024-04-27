package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

}
