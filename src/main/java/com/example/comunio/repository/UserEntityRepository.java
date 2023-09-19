package com.example.comunio.repository;

import com.example.comunio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByAlias(String alias);


}