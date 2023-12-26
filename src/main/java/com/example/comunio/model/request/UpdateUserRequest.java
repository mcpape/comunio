package com.example.comunio.model.request;

import lombok.Value;

@Value
public class UpdateUserRequest {

    String alias;
    Long bonus;
    String reason;

}
