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

    @Column(unique = true)
    private String name;
    private Long points;
    private Long balance;
    private Long marketValue;

    @ElementCollection
    @Column(name = "alias")
    @CollectionTable(name = "users_alias", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> alias = new ArrayList<>();

    @ElementCollection
    @Column(name = "bonus")
    @CollectionTable(name = "users_bonus", joinColumns = @JoinColumn(name = "owner_id"))
    private List<Long> bonus = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraBonusEntity> extraBonusEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fromUserEntity", orphanRemoval = true)
    private List<TransferEntity> fromTransferEntity = new ArrayList<>();

    @OneToMany(mappedBy = "toUserEntity", orphanRemoval = true)
    private List<TransferEntity> toTransferEntities = new ArrayList<>();

    public void addBonus(List<Long> bonus) {
        this.bonus.addAll(bonus);
    }

    public void addAlias(List<String> alias) {
        this.alias.addAll(alias);
    }

}
