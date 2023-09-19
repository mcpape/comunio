package com.example.comunio.domain.postscraping.bonus;

import com.example.comunio.entity.ExtraBonusEntity;
import com.example.comunio.entity.UserEntity;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TableUserInformationToEntityConverter {

    private final UserService userService;

    public List<ExtraBonusEntity> convert(List<UserExtraBonus> userExtraBonuses) {
        List<ExtraBonusEntity> results = new ArrayList<>();
        for (UserExtraBonus userExtraBonus : userExtraBonuses) {
            UserEntity userEntity = userService.findByNameOrAlias(userExtraBonus.getUserName());
            results.add(new ExtraBonusEntity(userExtraBonus.getBonus(), userExtraBonus.getBonusTime(), userEntity));
        }
        return results;
    }
}
