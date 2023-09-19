package com.example.comunio.controller;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.DefaultPostScrapingHandler;
import com.example.comunio.domain.postscraping.bonus.ExtraBonusHandler;
import com.example.comunio.domain.postscraping.points.PointsHandler;
import com.example.comunio.domain.postscraping.transfer.TransferHandler;
import com.example.comunio.entity.NewsContainerEntity;
import com.example.comunio.entity.TableContainerEntity;
import com.example.comunio.repository.NewsContainerEntityRepository;
import com.example.comunio.repository.TableContainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DummyController {

    private final NewsContainerEntityRepository newsContainerEntityRepository;
    private final TableContainerEntityRepository tableContainerEntityRepository;
    private final TransferHandler transferHandler;
    private final ExtraBonusHandler extraBonusHandler;
    private final PointsHandler pointsHandler;
    private final DefaultPostScrapingHandler defaultPostScrapingHandler;

    @GetMapping
    public void calcTransfers() {
        List<NewsContainerEntity> allNews = newsContainerEntityRepository.findAll();
        List<TableContainerEntity> allTables = tableContainerEntityRepository.findAll();
        ScrapingResult scrapingResult = new ScrapingResult(
                allNews.stream().map(NewsContainerEntity::getNews).toList(),
                allTables.stream().map(TableContainerEntity::getTableData).toList()
        );
        defaultPostScrapingHandler.startHandle(scrapingResult);
    }

}
