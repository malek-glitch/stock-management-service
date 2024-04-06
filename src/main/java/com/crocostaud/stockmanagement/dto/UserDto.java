package com.crocostaud.stockmanagement.dto;

import com.crocostaud.stockmanagement.model.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String name;
    String email;
    String password;
    String role;
}