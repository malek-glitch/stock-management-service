package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.UserDto;
import com.crocostaud.stockmanagement.model.ShopUser;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    String login(UserDto userDto);

    ShopUser getUser();

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    List<UserDto> getAll();

    void setShop(Long id, Long savedShopId);
}
