package com.example.comunio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "table_container")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableContainerEntity extends BaseEntity {

    private String tableData;

}
