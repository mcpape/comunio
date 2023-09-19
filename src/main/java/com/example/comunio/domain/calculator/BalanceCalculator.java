package com.example.comunio.domain.calculator;

import com.example.comunio.entity.ExtraBonusEntity;
import com.example.comunio.entity.TransferEntity;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class BalanceCalculator {

    private static final String COMPUTER = "Computer";
    private static final NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);

    @Value("${comnuio.money.per.point}")
    private String amountPerPoint;

    private final UserService userService;

    public List<BalanceResult> calculate() {
        List<UserEntity> users = userService.findUsers();

        List<BalanceResult> results = new ArrayList<>();
        for (UserEntity user : users) {
            if (user.getName().equals(COMPUTER)) {
                continue;
            }

            Long initAmount = user.getBalance();
            Long pointsAmount = calcAmountPerPoints(user);
            Long extraBonus = calcExtraBonus(user);
            Long bonus = calcBonus(user);
            Long transferAmount = calcTransfer(user);

            Long totalBalance = initAmount + pointsAmount + extraBonus + bonus + transferAmount;
            Long marketValue = user.getMarketValue();
            Long creditLimit = (marketValue / 4) + totalBalance;
            Long value = marketValue + totalBalance;
            results.add(new BalanceResult(user.getName(), nf.format(totalBalance), nf.format(creditLimit), nf.format(value)));
        }

        return results;
    }

    private Long calcTransfer(UserEntity user) {
        Long fromAmount = user.getFromTransferEntity()
                .stream()
                .map(TransferEntity::getPrice)
                .reduce(0L, Long::sum);

        Long toAmount = user.getToTransferEntities()
                .stream()
                .map(TransferEntity::getPrice)
                .reduce(0L, Long::sum);

        return fromAmount - toAmount;
    }

    private Long calcAmountPerPoints(UserEntity userEntity) {
        return userEntity.getPoints() * Long.parseLong(amountPerPoint);
    }

    private Long calcExtraBonus(UserEntity userEntity) {
        return userEntity.getExtraBonusEntities()
                .stream()
                .map(ExtraBonusEntity::getAmount)
                .reduce(0L, Long::sum);
    }

    private Long calcBonus(UserEntity userEntity) {
        return userEntity.getBonus()
                .stream()
                .reduce(0L, Long::sum);
    }

}
