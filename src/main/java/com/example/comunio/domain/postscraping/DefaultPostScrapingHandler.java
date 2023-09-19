package com.example.comunio.domain.postscraping;

import com.example.comunio.domain.ScrapingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class DefaultPostScrapingHandler {

    private final List<PostScrapingHandler> processors;

    @Autowired
    public DefaultPostScrapingHandler(List<PostScrapingHandler> processors) {
        this.processors = processors.stream()
                .sorted(Comparator.comparing(PostScrapingHandler::getProcessOrder))
                .toList();
    }

    public void startHandle(ScrapingResult scrapedResult) {
        processors.forEach(processor -> processor.handle(scrapedResult));
    }
}
