package com.example.comunio.model.request;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class UpdateUserRequest {

    List<String> alias = new ArrayList<>();
    List<Long> bonus = new ArrayList<>();

}
