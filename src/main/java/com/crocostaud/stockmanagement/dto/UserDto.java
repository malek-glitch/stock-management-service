package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.ShopUser;
import com.crocostaud.stockmanagement.model.utils.Role;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ShopUser}
 */
@Value
@Builder
public class UserDto implements Serializable {
    Long id;
    String username;
    String email;
    String password;
    Set<Role> roles;

    Long ShopId;
}