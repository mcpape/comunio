package com.example.comunio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "news_container")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsContainerEntity extends BaseEntity {

    private String news;

}
