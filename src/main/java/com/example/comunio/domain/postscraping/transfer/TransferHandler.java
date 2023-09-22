package com.example.comunio.domain.postscraping.transfer;

import com.example.comunio.domain.ScrapingResult;
import com.example.comunio.domain.postscraping.PostScrapingHandler;
import com.example.comunio.entity.TransferEntity;
import com.example.comunio.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Will save all transfers which are scraped.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransferHandler implements PostScrapingHandler {

    private final TransferService transferService;
    private final TransferPreparer transferPreparer;
    private final TransferToEntityConverter transferToEntityConverter;

    @Override
    public void handle(ScrapingResult scrapingResult) {
        log.info("Starting with TransferHandler");
        List<String> newsData = scrapingResult.getNewsData();
        List<PreparedTransfer> preparedNewsData = transferPreparer.prepare(newsData);
        log.info("Prepared news size " + preparedNewsData.size());
        List<TransferEntity> transferEntities = transferToEntityConverter.convert(preparedNewsData);
        log.info("Created transfer entities " + transferEntities.size());
        transferService.saveTransfersIfNotExist(transferEntities);
    }

    @Override
    public int getProcessOrder() {
        return TRANSFER_HANDLER;
    }
}
