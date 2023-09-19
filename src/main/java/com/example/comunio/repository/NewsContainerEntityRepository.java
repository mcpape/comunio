package com.example.comunio.repository;

import com.example.comunio.entity.NewsContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsContainerEntityRepository extends JpaRepository<NewsContainerEntity, Long> {
}