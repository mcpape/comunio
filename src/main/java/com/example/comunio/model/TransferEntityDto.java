package com.example.comunio.model;

import com.example.comunio.model.PlayerEntityDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.comunio.entity.TransferEntity}
 */
@Value
public class TransferEntityDto implements Serializable {
    Long id;
    LocalDate transferDate;
    String transferTime;
    Long price;
    PlayerEntityDto playerEntity;
}