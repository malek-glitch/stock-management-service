package com.crocostaud.stockmanagement.dto.stock;

import com.crocostaud.stockmanagement.model.stock.ShopUser;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

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
    String phone;
    LocalDate createdAt;
    Long shopId;
}