package com.example.comunio.model;

import lombok.Value;

import java.util.List;

@Value
public class CreateUserRequest {

    String name;
    List<String> aliases;
    List<Long> bonus;

}
