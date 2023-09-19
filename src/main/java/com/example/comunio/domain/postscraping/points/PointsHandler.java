package com.example.comunio.domain.postscraping.points;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.PostScrapingHandler;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PointsHandler implements PostScrapingHandler {

    private final PointsPreparer pointsPreparer;
    private final UserService userService;

    @Override
    public void handle(ScrapingResult scrapingResult) {
        log.info("Starting with PointsHandler calc.");
        List<String> tableData = scrapingResult.getTableData();
        List<TableUserInformation> tableUserInformation = pointsPreparer.prepare(tableData);
        userService.updateUserInformation(tableUserInformation);
    }

    @Override
    public int getProcessOrder() {
        return POINTS_HANDLER;
    }
}
