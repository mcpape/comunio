package com.example.comunio.model.request;

import lombok.Value;

import java.util.List;

@Value
public class CreateUserRequest {

    String name;
    List<String> aliases;
    List<Long> bonus;

}
