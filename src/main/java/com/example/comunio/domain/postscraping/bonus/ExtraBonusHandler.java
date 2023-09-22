package com.example.comunio.domain.postscraping.bonus;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.PostScrapingHandler;
import com.example.comunio.entity.ExtraBonusEntity;
import com.example.comunio.service.ExtraBonusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Will handle extra bonuses for players that loses the game day.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ExtraBonusHandler implements PostScrapingHandler {

    private final ExtraBonusPreparer extraBonusPreparer;
    private final TableUserInformationToEntityConverter tableUserInformationToEntityConverter;
    private final ExtraBonusService extraBonusService;

    @Override
    public void handle(ScrapingResult scrapingResult) {
        log.info("Starting with ExtraBonusHandler");
        List<String> newsData = scrapingResult.getNewsData();
        List<UserExtraBonus> userExtraBonuses = extraBonusPreparer.prepare(newsData);
        List<ExtraBonusEntity> extraBonusEntities = tableUserInformationToEntityConverter.convert(userExtraBonuses);
        extraBonusService.saveExtraBonusEntitiesIfNotExists(extraBonusEntities);
    }

    @Override
    public int getProcessOrder() {
        return EXTRA_BONUS_HANDLER;
    }
}
