package com.example.comunio.domain.postscraping.transfer;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PreparedTransfer {

    LocalDate transferDate;
    String transferTime;
    String transferedPlayer;
    Long price;
    String fromPlayer;
    String toPlayer;

}
