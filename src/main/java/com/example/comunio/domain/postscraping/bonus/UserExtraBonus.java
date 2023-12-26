package com.example.comunio.domain.postscraping.bonus;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserExtraBonus {

    LocalDate bonusTime;
    String userName;
    Long bonus;
    String reason;

}
