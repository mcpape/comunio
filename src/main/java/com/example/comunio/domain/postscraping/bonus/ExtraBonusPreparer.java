package com.example.comunio.domain.postscraping.bonus;

import com.example.comunio.util.DateParser;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ExtraBonusPreparer {

    private static final String EXTRA_BONUS = "Gutschrift:";
    private static final String FINE = "Disziplinarstrafe:";
    private static final String START_PLAYER = "wurden";
    private static final String END_PLAYER = "vom";
    private static final String REASON = "Begründung:";

    public List<UserExtraBonus> prepare(List<String> scrapedResult) {
        List<UserExtraBonus> results = new ArrayList<>();
        LocalDate bonusDate = LocalDate.now();
        for (String newsBlock : scrapedResult) {
            String[] singleNewsLines = newsBlock.split("\n");
            for (String singleNewsLine : singleNewsLines) {
                bonusDate = DateParser.parseToDate(singleNewsLine)
                        .orElse(bonusDate);

                List<String> newsLineSingleWords = Arrays.asList(singleNewsLine.split(" "));
                if (newsLineSingleWords.stream().noneMatch(s -> s.equals(EXTRA_BONUS) || s.equals(FINE))) {
                    continue;
                }

                int indexGutschrift;
                if (newsLineSingleWords.contains(EXTRA_BONUS)) {
                    indexGutschrift = newsLineSingleWords.indexOf(EXTRA_BONUS) + 1;
                } else {
                    indexGutschrift = newsLineSingleWords.indexOf(FINE) + 1;
                }

                Long amount = extractBonusAmount(newsLineSingleWords, indexGutschrift);
                int indexReason = newsLineSingleWords.indexOf(REASON) + 1;
                String reason = extractReason(newsLineSingleWords, indexReason);
                if (newsLineSingleWords.contains(FINE)) {
                    amount *= -1;
                }

                String playerName = extractPlayername(newsLineSingleWords);

                results.add(new UserExtraBonus(bonusDate, playerName, amount, reason));
            }
        }

        return results;
    }

    private String extractReason(List<String> newsLineSingleWords, int indexReason) {
        return newsLineSingleWords.get(indexReason);
    }

    private Long extractBonusAmount(List<String> newsLineSingleWords, int indexGutschrift) {
        NumberFormat nf = NumberFormat.getInstance();
        long amount;
        try {
            amount = nf.parse(newsLineSingleWords.get(indexGutschrift)).longValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    private String extractPlayername(List<String> newsLineSingleWords) {
        int startPlayerIndex = newsLineSingleWords.indexOf(START_PLAYER) + 1;
        int endPlayerIndex = newsLineSingleWords.indexOf(END_PLAYER);
        List<String> playerNameList = newsLineSingleWords.subList(startPlayerIndex, endPlayerIndex);
        return String.join("", playerNameList);
    }

}
