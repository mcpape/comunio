package com.example.comunio.domain.postscraping.points;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointsPreparer {

    public List<TableUserInformation> prepare(List<String> tableData) {
        List<TableUserInformation> results = new ArrayList<>();
        for (String data : tableData) {
            String[] splitedData = data.split("\n");

            String userName;
            String marketValue;
            String value;

            if (splitedData.length == 4) { // ersten drei plätze haben keine nummer
                userName = splitedData[0];
                marketValue = splitedData[1];
                value = splitedData[3];
            } else { // ab 4. platz steht die nummer mit dabei
                userName = splitedData[1];
                marketValue = splitedData[2];
                value = splitedData[4];
            }
            Long pointsAsLong = Long.valueOf(value);
            Long marketValueAsLong = Long.valueOf(marketValue.replace(".", ""));

            results.add(new TableUserInformation(userName, pointsAsLong, marketValueAsLong));
        }
        return results;
    }
}
