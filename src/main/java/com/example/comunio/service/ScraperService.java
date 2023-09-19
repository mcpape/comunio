package com.example.comunio.service;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.DefaultPostScrapingHandler;
import com.example.comunio.domain.scrap.Scraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScraperService {

    private final Scraper scraper;
    private final DefaultPostScrapingHandler defaultPostScrapingHandler;
    private final NewsContainerService newsContainerService;

    public void scrapNews() {
        String stopDate = newsContainerService.getStopDate();
        ScrapingResult scrapedResult = scraper.scrap(stopDate);
        defaultPostScrapingHandler.startHandle(scrapedResult);
    }

}
