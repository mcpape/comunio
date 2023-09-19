package com.example.comunio.service;

import com.example.comunio.entity.TransferEntity;
import com.example.comunio.repository.TransferEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferEntityRepository transferEntityRepository;

    public List<TransferEntity> saveTransfersIfNotExist(List<TransferEntity> transferEntities) {
        List<TransferEntity> results = new ArrayList<>();
        for (TransferEntity transferEntity : transferEntities) {
            results.add(transferEntityRepository.findByTransferDateAndTransferTimeAndFromUserEntityAndToUserEntityAndPlayerEntity(
                            transferEntity.getTransferDate(),
                            transferEntity.getTransferTime(),
                            transferEntity.getFromUserEntity(),
                            transferEntity.getToUserEntity(),
                            transferEntity.getPlayerEntity())
                    .orElseGet(() -> transferEntityRepository.save(transferEntity)));
        }
        return results;
    }

}
