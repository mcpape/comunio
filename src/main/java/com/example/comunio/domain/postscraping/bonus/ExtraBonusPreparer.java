package com.example.comunio.domain.postscraping.bonus;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class ExtraBonusPreparer {

    private static final String EXTRA_BONUS = "Gutschrift:";
    private static final String START_PLAYER = "wurden";
    private static final String END_PLAYER = "vom";

    public List<UserExtraBonus> prepare(List<String> scrapedResult) {
        List<UserExtraBonus> results = new ArrayList<>();
        LocalDate bonusDate = LocalDate.now();
        for (String newsBlock : scrapedResult) {
            String[] singleNewsLines = newsBlock.split("\n");
            for (String singleNewsLine : singleNewsLines) {
                bonusDate = parseBonusDate(singleNewsLine)
                        .orElse(bonusDate);

                List<String> newsLineSingleWords = Arrays.asList(singleNewsLine.split(" "));
                if (newsLineSingleWords.stream().noneMatch(s -> s.equals(EXTRA_BONUS))) {
                    continue;
                }

                int indexGutschrift = newsLineSingleWords.indexOf(EXTRA_BONUS) + 1;
                Long amount = extractBonusAmount(newsLineSingleWords, indexGutschrift);
                String playerName = extractPlayername(newsLineSingleWords);

                results.add(new UserExtraBonus(bonusDate, playerName, amount));
            }
        }

        return results;
    }

    private static Long extractBonusAmount(List<String> newsLineSingleWords, int indexGutschrift) {
        NumberFormat nf = NumberFormat.getInstance();
        Long amount;
        try {
            amount = nf.parse(newsLineSingleWords.get(indexGutschrift)).longValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    private static String extractPlayername(List<String> newsLineSingleWords) {
        int startPlayerIndex = newsLineSingleWords.indexOf(START_PLAYER) + 1;
        int endPlayerIndex = newsLineSingleWords.indexOf(END_PLAYER);
        List<String> playerNameList = newsLineSingleWords.subList(startPlayerIndex, endPlayerIndex);
        String playerName = String.join("", playerNameList);
        return playerName;
    }

    private Optional<LocalDate> parseBonusDate(String newsBlock) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        LocalDate localDateNow = LocalDate.now();
        LocalDate localDateYesterday = localDateNow.minusDays(1);

        Pattern datePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{2}");

        Predicate<String> todayDatePredicate = x -> x.equals("Heute");
        Predicate<String> yesterdayDatePredicate = x -> x.equals("Gestern");

        LocalDate date;
        if (todayDatePredicate.test(newsBlock)) {
            date = localDateNow;
        } else if (yesterdayDatePredicate.test(newsBlock)) {
            date = localDateYesterday;
        } else if (datePattern.matcher(newsBlock).matches()) {
            date = LocalDate.parse(newsBlock, dateFormatter);
        } else {
            return Optional.empty();
        }

        return Optional.of(date);
    }
}
