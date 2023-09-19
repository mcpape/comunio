package com.example.comunio.controller;

import com.example.comunio.domain.postscraping.DefaultPostScrapingHandler;
import com.example.comunio.domain.postscraping.bonus.ExtraBonusHandler;
import com.example.comunio.domain.postscraping.points.PointsHandler;
import com.example.comunio.domain.postscraping.transfer.TransferHandler;
import com.example.comunio.repository.NewsContainerEntityRepository;
import com.example.comunio.repository.TableContainerEntityRepository;
import com.example.comunio.service.NewsContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final NewsContainerService newsContainerService;

    @GetMapping
    public String calcTransfers() {
//        List<NewsContainerEntity> allNews = newsContainerEntityRepository.findAll();
//        List<TableContainerEntity> allTables = tableContainerEntityRepository.findAll();
//        ScrapingResult scrapingResult = new ScrapingResult(
//                allNews.stream().map(NewsContainerEntity::getNews).toList(),
//                allTables.stream().map(TableContainerEntity::getTableData).toList()
//        );
//        defaultPostScrapingHandler.startHandle(scrapingResult);
        return newsContainerService.getStopDate();
    }

}
