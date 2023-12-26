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

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomBonusEntity> customBonusEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraBonusEntity> extraBonusEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fromUserEntity", orphanRemoval = true)
    private List<TransferEntity> fromTransferEntity = new ArrayList<>();

    @OneToMany(mappedBy = "toUserEntity", orphanRemoval = true)
    private List<TransferEntity> toTransferEntities = new ArrayList<>();

    public void addBonus(CustomBonusEntity customBonusEntity) {
        this.customBonusEntities.add(customBonusEntity);
    }

    public void addAlias(String alias) {
        this.alias.add(alias);
    }

}
