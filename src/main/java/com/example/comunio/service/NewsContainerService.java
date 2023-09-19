package com.example.comunio.service;

import com.example.comunio.entity.NewsContainerEntity;
import com.example.comunio.repository.NewsContainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsContainerService {

    @Value("${comnuio.league.start.date}")
    private String leagueStartDate;
    private final NewsContainerEntityRepository newsContainerEntityRepository;

    //TODO adjust stop date. depends on last scraping
    public String getStopDate() {
        return leagueStartDate;
    }

    public List<NewsContainerEntity> createNewsContainerEntities(List<String> scrapeResult) {
        List<NewsContainerEntity> entities = scrapeResult.stream()
                .map(result -> NewsContainerEntity.builder()
                        .news(result)
                        .build())
                .toList();
        return newsContainerEntityRepository.saveAll(entities);
    }

}
