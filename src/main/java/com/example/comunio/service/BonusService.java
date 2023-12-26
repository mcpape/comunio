package com.example.comunio.service;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.bonus.ExtraBonusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusService {

    private final NewsContainerService newsContainerService;
    private final ExtraBonusHandler extraBonusHandler;

    public void parseAgain() {
        ScrapingResult scrapingResult = createScrapingResult();
        extraBonusHandler.handle(scrapingResult);
    }

    private ScrapingResult createScrapingResult() {
        List<String> allNewsData = newsContainerService.getAllData();
        return new ScrapingResult(allNewsData, List.of());
    }
}
