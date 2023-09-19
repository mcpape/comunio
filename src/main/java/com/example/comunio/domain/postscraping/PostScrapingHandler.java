package com.example.comunio.domain.postscraping;

import com.example.comunio.domain.ScrapingResult;

public interface PostScrapingHandler {

    /**
     * Should run at first, so that the plain result is saved
     */
    int SAVE_HANDLER = 1;
    int TRANSFER_HANDLER = 2;
    int EXTRA_BONUS_HANDLER = 3;
    int POINTS_HANDLER = 4;

    void handle(ScrapingResult scrapingResult);

    int getProcessOrder();
}
