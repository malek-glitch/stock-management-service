package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.UserDto;
import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.model.ShopUser;
import com.crocostaud.stockmanagement.repository.UserRepository;
import com.crocostaud.stockmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        ShopUser user = ShopUser.builder()
                .username(userDto.getUsername())
                .password(encryptPassword(userDto.getPassword()))
                .email(userDto.getEmail())
                .roles(userDto.getRoles())
                .shop(new Shop(userDto.getShopId()))
                .build();
        ShopUser savedUser = userRepo.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        if (!userDto.getId().equals(id))
            return null;
        ShopUser user;
        try {
            user = userRepo.getReferenceById(id);
        } catch (Exception e) {
            return null;
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(encryptPassword(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());

        userRepo.updateUsernameAndPasswordAndEmailByIdAllIgnoreCase(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                id
        );
        return mapToDto(userRepo.getReferenceById(id));
    }

    @Override
    public UserDto getUserById(Long id) {
        try {
            return mapToDto(userRepo.getReferenceById(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    private UserDto mapToDto(ShopUser user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
