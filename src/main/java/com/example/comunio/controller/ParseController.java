package com.example.comunio.controller;

import com.example.comunio.service.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parser")
@RequiredArgsConstructor
public class ParseController {

    private final ScraperService scraperService;

    @GetMapping
    public void parse() {
        scraperService.scrapNews();
    }

}
