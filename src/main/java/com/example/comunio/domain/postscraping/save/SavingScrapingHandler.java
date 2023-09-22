package com.example.comunio.domain.postscraping.save;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.PostScrapingHandler;
import com.example.comunio.service.NewsContainerService;
import com.example.comunio.service.TableContainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Will save all scraped results
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SavingScrapingHandler implements PostScrapingHandler {

    private final NewsContainerService newsContainerService;
    private final TableContainerService tableContainerService;

    @Override
    public void handle(ScrapingResult scrapingResult) {
        log.info("Starting with SavingScrapingHandler");
        newsContainerService.saveNewsContainerEntitiesIfNotExists(scrapingResult.getNewsData());
        tableContainerService.removeAllAndResaveTableContainerEntities(scrapingResult.getTableData());
    }

    @Override
    public int getProcessOrder() {
        return SAVE_HANDLER;
    }
}
