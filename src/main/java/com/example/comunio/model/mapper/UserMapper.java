package com.example.comunio.model.mapper;

import com.example.comunio.entity.CustomBonusEntity;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.model.dto.UserDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {
    @Override
    public UserDto mapToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .alias(userEntity.getAlias())
                .marketValue(userEntity.getMarketValue())
                .points(userEntity.getPoints())
                .bonus(userEntity.getCustomBonusEntities()
                        .stream()
                        .map(CustomBonusEntity::getAmount)
                        .toList())
                .build();
    }

    @Override
    public UserEntity mapToEntity(UserEntity userEntity) {
        throw new NotImplementedException();
    }
}
