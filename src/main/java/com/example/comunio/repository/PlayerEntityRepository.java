package com.example.comunio.repository;

import com.example.comunio.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByName(String name);
}