package com.example.comunio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferEntity extends BaseEntity {

    private LocalDate transferDate;
    private String transferTime;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "player_entity_id")
    private PlayerEntity playerEntity;

    @ManyToOne
    @JoinColumn(name = "from_user_entity_id")
    private UserEntity fromUserEntity;

    @ManyToOne
    @JoinColumn(name = "to_user_entity_id")
    private UserEntity toUserEntity;

}
