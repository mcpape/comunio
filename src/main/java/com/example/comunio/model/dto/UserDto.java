package com.example.comunio.model.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.comunio.entity.UserEntity}
 */
@Value
@Builder
public class UserDto implements Serializable {
    Long id;
    String name;
    Long points;
    Long marketValue;
    List<String> alias;
    List<Long> bonus;
}