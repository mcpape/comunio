package com.example.comunio.domain;

import lombok.Value;

import java.util.List;

@Value
public class ScrapingResult {

    List<String> newsData;
    List<String> tableData;

}
