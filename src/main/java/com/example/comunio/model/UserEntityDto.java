package com.example.comunio.model;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.comunio.entity.UserEntity}
 */
@Value
public class UserEntityDto implements Serializable {
    Long id;
    String name;
    List<String> alias;
    Long points;
    Long balance;
    List<Long> bonus;
}