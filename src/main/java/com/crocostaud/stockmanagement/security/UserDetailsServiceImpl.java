package com.crocostaud.stockmanagement.security;

import com.crocostaud.stockmanagement.model.ShopUser;
import com.crocostaud.stockmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(username);

        return User.withUsername(username)
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
