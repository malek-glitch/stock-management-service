package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.utils.security.Token;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto, Long ShopId);

    UserDto updateUser(UserDto userDto, Long id);

    Token login(UserDto userDto);

    ShopUser getUser();

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    List<UserDto> getAll(Long shopId);

    void setShop(Long id, Long savedShopId);
}
