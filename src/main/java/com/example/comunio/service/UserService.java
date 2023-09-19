package com.example.comunio.service;

import com.example.comunio.domain.postscraping.points.TableUserInformation;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.model.CreateUserRequest;
import com.example.comunio.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${comunio.start.money}")
    private String startMoneyAmount;

    private final UserEntityRepository userEntityRepository;

    public void createUsers(List<CreateUserRequest> createUserRequests) {
        List<UserEntity> players = createUserRequests.stream()
                .map(request -> UserEntity.builder()
                        .name(request.getName())
                        .alias(request.getAliases())
                        .bonus(request.getBonus())
                        .balance(Long.valueOf(startMoneyAmount))
                        .build()
                )
                .toList();
        userEntityRepository.saveAll(players);
    }

    public List<UserEntity> findUsers() {
        return userEntityRepository.findAll();
    }

    public UserEntity findByNameOrAlias(String name) {
        return userEntityRepository.findByName(name)
                .or(() -> userEntityRepository.findByAlias(name))
                .orElseThrow(() -> new EntityNotFoundException("No user for name %s found.".formatted(name)));
    }

    public void updateUserInformation(List<TableUserInformation> tableUserInformation) {
        for (TableUserInformation userInformation : tableUserInformation) {
            UserEntity userEntity = findByNameOrAlias(userInformation.getUserName());
            userEntity.setPoints(userInformation.getPoints());
            userEntity.setMarketValue(userInformation.getMarketValue());
            userEntityRepository.save(userEntity);
        }
    }

}
