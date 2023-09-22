package com.example.comunio.service;

import com.example.comunio.domain.postscraping.points.TableUserInformation;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.model.dto.UserDto;
import com.example.comunio.model.mapper.UserMapper;
import com.example.comunio.model.request.CreateUserRequest;
import com.example.comunio.model.request.UpdateUserRequest;
import com.example.comunio.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String COMPUTER = "Computer";

    @Value("${comunio.start.money}")
    private String startMoneyAmount;

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

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

    public List<UserEntity> findUsersWithoutComputer() {
        return findUsers().stream()
                .filter(user -> !user.getName().equals(COMPUTER))
                .toList();
    }

    public List<UserEntity> findUsers() {
        return userEntityRepository.findAll();
    }

    public List<UserDto> findUserDtos() {
        return findUsers().stream()
                .map(userMapper::mapToDto)
                .toList();
    }

    public UserEntity findByNameOrAlias(String name) {
        return userEntityRepository.findByName(name)
                .or(() -> userEntityRepository.findByAlias(name))
                .orElseThrow(() -> new EntityNotFoundException("No user with name %s found.".formatted(name)));
    }

    public void updateUserInformation(List<TableUserInformation> tableUserInformation) {
        for (TableUserInformation userInformation : tableUserInformation) {
            UserEntity userEntity = findByNameOrAlias(userInformation.getUserName());
            userEntity.setPoints(userInformation.getPoints());
            userEntity.setMarketValue(userInformation.getMarketValue());
            userEntityRepository.save(userEntity);
        }
    }

    public Optional<UserDto> updateUser(Long id, UpdateUserRequest updateUserRequest) {
        return userEntityRepository.findById(id)
                .map(user -> {
                    user.addAlias(updateUserRequest.getAlias());
                    user.addBonus(updateUserRequest.getBonus());
                    return user;
                })
                .map(userEntityRepository::save)
                .map(userMapper::mapToDto);
    }
}
