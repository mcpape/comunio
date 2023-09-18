package com.example.comunio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    private String name;

    @ElementCollection
    @Column(name = "alias")
    @CollectionTable(name = "users_alias", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> alias = new ArrayList<>();

    private Long points;
    private Long balance;

    @ElementCollection
    @Column(name = "bonus")
    @CollectionTable(name = "users_bonus", joinColumns = @JoinColumn(name = "owner_id"))
    private List<Long> bonus = new ArrayList<>();

}
