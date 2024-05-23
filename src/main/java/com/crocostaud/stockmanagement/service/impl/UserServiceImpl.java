package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.UserDto;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.repository.UserRepository;
import com.crocostaud.stockmanagement.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder bcryptEncoder;

    private final UserRepository userRepo;

    private final JwtServiceImpl jwtService;

    private final AuthenticationProvider authenticationProvider;

    public UserServiceImpl(PasswordEncoder bcryptEncoder, UserRepository userRepo, JwtServiceImpl jwtService, AuthenticationProvider authenticationProvider) {
        this.bcryptEncoder = bcryptEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        ShopUser user = ShopUser.builder().username(userDto.getUsername()).password(encryptPassword(userDto.getPassword())).email(userDto.getEmail()).role(userDto.getRole()).build();

        if (userDto.getShopId() != null) user.setShop(new Shop(userDto.getShopId()));

        ShopUser savedUser = null;
        try {
            savedUser = userRepo.save(user);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return mapToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        if (!userDto.getId().equals(id)) return null;

        userRepo.updateUsernameAndPasswordAndEmailAndPhoneByIdAllIgnoreCase(userDto.getUsername(), encryptPassword(userDto.getPassword()), userDto.getEmail(), userDto.getPhone(), id);
        return mapToDto(userRepo.getReferenceById(id));
    }

    @Override
    public String login(UserDto userDto) {
        final Authentication authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final ShopUser user = userRepo.findByUsername(userDto.getUsername());
        return jwtService.generateToken(user);
    }

    @Override
    public ShopUser getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByUsername(userDetails.getUsername());
    }

    @Override
    public UserDto getUserById(Long id) {
        try {
            return mapToDto(userRepo.findById(id).orElse(null));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    public void setShop(Long userId, Long shopId) {
        userRepo.setShopById(new Shop(shopId), userId);
    }

    private UserDto mapToDto(ShopUser user) {
        if (user == null) return null;

        Long ShopId = user.getShop() != null ? user.getShop().getId() : null;

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .phone(user.getPhone())
                .email(user.getEmail())
                .shopId(ShopId)
                .role(user.getRole())
                .build();
    }

    private String encryptPassword(String password) {
        return bcryptEncoder.encode(password);
    }
}
