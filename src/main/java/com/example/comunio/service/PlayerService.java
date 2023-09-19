package com.example.comunio.service;

import com.example.comunio.entity.PlayerEntity;
import com.example.comunio.repository.PlayerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerEntityRepository playerEntityRepository;

    public PlayerEntity findOrCreatePlayer(String playerName) {
        return playerEntityRepository.findByName(playerName)
                .orElseGet(() -> playerEntityRepository.save(PlayerEntity.builder()
                        .name(playerName)
                        .build()));
    }

}
