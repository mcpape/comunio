package com.example.comunio.domain.calculator;

import lombok.Value;

@Value
public class BalanceResult {

    String user;
    String balance;
    String creditLimit;
    String value;

}
