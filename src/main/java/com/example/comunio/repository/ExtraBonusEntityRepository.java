package com.example.comunio.repository;

import com.example.comunio.entity.ExtraBonusEntity;
import com.example.comunio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExtraBonusEntityRepository extends JpaRepository<ExtraBonusEntity, Long> {
    Optional<ExtraBonusEntity> findByUserEntityAndAmountAndBonusTime(UserEntity userEntity, Long amount, LocalDate bonusTime);


}