package com.example.comunio.domain.postscraping.transfer;

import com.example.comunio.entity.PlayerEntity;
import com.example.comunio.entity.TransferEntity;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.service.PlayerService;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransferToEntityConverter {

    private final UserService userService;
    private final PlayerService playerService;

    public List<TransferEntity> convert(List<PreparedTransfer> preparedNewsData) {
        List<TransferEntity> transferEntities = new ArrayList<>();
        for (PreparedTransfer preparedTransfer : preparedNewsData) {
            PlayerEntity playerEntity = playerService.findOrCreatePlayer(preparedTransfer.getTransferedPlayer());
            UserEntity fromUserEntity = userService.findByNameOrAlias(preparedTransfer.getFromPlayer());
            UserEntity toUserEntity = userService.findByNameOrAlias(preparedTransfer.getToPlayer());

            transferEntities.add(new TransferEntity(preparedTransfer.getTransferDate(),
                    preparedTransfer.getTransferTime(),
                    preparedTransfer.getPrice(),
                    playerEntity,
                    fromUserEntity,
                    toUserEntity));
        }

        return transferEntities;
    }
}
