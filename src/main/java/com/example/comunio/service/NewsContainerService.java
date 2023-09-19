package com.example.comunio.service;

import com.example.comunio.entity.NewsContainerEntity;
import com.example.comunio.repository.NewsContainerEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsContainerService {

    @Value("${comnuio.league.start.date}")
    private String leagueStartDate;
    private final NewsContainerEntityRepository newsContainerEntityRepository;

    /**
     * @return A date in string representation. It is the last time since scraping minus 3 day.
     * Or league start date if nothing is found in DB.
     * The minus days should compensate the 'today' and 'yesterday' days in the scraping result.
     */
    public String getStopDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        return newsContainerEntityRepository.findTopByOrderByCreatedAtDesc()
                .map(result -> result.getCreatedAt().toLocalDate().minusDays(3).format(dateFormatter))
                .orElse(leagueStartDate);
    }

    public List<NewsContainerEntity> saveNewsContainerEntitiesIfNotExists(List<String> scrapedResult) {
        removeOldEntryWhichIsNotUpToDate("Heute");
        removeOldEntryWhichIsNotUpToDate("Gestern");
        List<NewsContainerEntity> results = new ArrayList<>();
        for (String news : scrapedResult) {
            results.add(newsContainerEntityRepository.findByNews(news)
                    .orElseGet(() -> NewsContainerEntity.builder()
                            .news(news)
                            .build()));
        }
        return newsContainerEntityRepository.saveAll(results);
    }

    private void removeOldEntryWhichIsNotUpToDate(String dateToDelete) {
        NewsContainerEntity newsContainer = newsContainerEntityRepository.findByNewsStartsWith(dateToDelete)
                .orElseThrow(() -> new IllegalStateException("Should be matching entry"));
        newsContainerEntityRepository.delete(newsContainer);
    }

}
