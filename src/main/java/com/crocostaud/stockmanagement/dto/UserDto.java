package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.ShopUser;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ShopUser}
 */

@Builder
@Value
public class UserDto implements Serializable {
    Long id;
    String username;
    String email;
    String password;
    String role;

    Long shopId;


}