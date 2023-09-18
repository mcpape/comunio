package com.example.comunio.model;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.comunio.entity.PlayerEntity}
 */
@Value
public class PlayerEntityDto implements Serializable {
    Long id;
    String name;
}