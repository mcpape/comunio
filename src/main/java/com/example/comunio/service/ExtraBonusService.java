package com.example.comunio.service;

import com.example.comunio.entity.ExtraBonusEntity;
import com.example.comunio.repository.ExtraBonusEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtraBonusService {

    private final ExtraBonusEntityRepository extraBonusEntityRepository;

    public List<ExtraBonusEntity> saveExtraBonusEntitiesIfNotExists(List<ExtraBonusEntity> extraBonusEntities) {
        List<ExtraBonusEntity> results = new ArrayList<>();
        for (ExtraBonusEntity extraBonusEntity : extraBonusEntities) {
            results.add(extraBonusEntityRepository.findByUserEntityAndAmountAndBonusTime(extraBonusEntity.getUserEntity(),
                            extraBonusEntity.getAmount(), extraBonusEntity.getBonusTime())
                    .orElseGet(() -> extraBonusEntityRepository.save(extraBonusEntity)));
        }
        return results;
    }

}
