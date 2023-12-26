package com.example.comunio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "custom_bonus")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomBonusEntity extends BaseEntity {

    private Long amount;
    private LocalDate bonusTime;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

}
