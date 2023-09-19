package com.example.comunio.repository;

import com.example.comunio.entity.PlayerEntity;
import com.example.comunio.entity.TransferEntity;
import com.example.comunio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TransferEntityRepository extends JpaRepository<TransferEntity, Long> {
    Optional<TransferEntity> findByTransferDateAndTransferTimeAndFromUserEntityAndToUserEntityAndPlayerEntity(LocalDate transferDate, String transferTime, UserEntity fromUserEntity, UserEntity toUserEntity, PlayerEntity playerEntity);
}