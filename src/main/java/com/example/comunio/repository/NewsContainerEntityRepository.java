package com.example.comunio.repository;

import com.example.comunio.entity.NewsContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsContainerEntityRepository extends JpaRepository<NewsContainerEntity, Long> {
    Optional<NewsContainerEntity> findTopByOrderByCreatedAtDesc();

    Optional<NewsContainerEntity> findByNews(String news);

    Optional<NewsContainerEntity> findByNewsStartsWith(String news);

}