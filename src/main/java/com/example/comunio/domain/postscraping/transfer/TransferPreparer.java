package com.example.comunio.domain.postscraping.transfer;

import com.example.comunio.util.DateParser;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TransferPreparer {

    private static final String CHANGES_VERB = "wechselt";
    private static final String FOR_PRICE = "für";
    private static final String FROM_PLAYER = "von";
    private static final String TO_PLAYER = "zu";
    private static final String DEFAULT_TRANSFER_TIME = "24:24";

    public List<PreparedTransfer> prepare(List<String> scrapedResult) {
        List<PreparedTransfer> result = new ArrayList<>();
        LocalDate transferDate = LocalDate.now();
        for (String newsBlock : scrapedResult) {
            String[] singleNewsLines = newsBlock.split("\n");
            for (String singleNewsLine : singleNewsLines) {
                transferDate = DateParser.parseToDate(singleNewsLine)
                        .orElse(transferDate);

                List<String> newsLineSingleWords = Arrays.asList(singleNewsLine.split(" "));
                if (!new HashSet<>(newsLineSingleWords).containsAll(List.of(CHANGES_VERB, TO_PLAYER, FROM_PLAYER))) {
                    continue;
                }

                String transferTime = parseTransferTime(newsLineSingleWords);
                String transferedPlayer = parseTransferedPlayer(newsLineSingleWords);
                Long transferAmount = parseTransferAmount(newsLineSingleWords);

                int fromIndex = newsLineSingleWords.indexOf(FROM_PLAYER);
                int toIndex = newsLineSingleWords.indexOf(TO_PLAYER);
                String fromPlayer = parseFromPlayer(newsLineSingleWords, fromIndex, toIndex);
                String toPlayer = parseToPlayer(newsLineSingleWords, toIndex);

                result.add(new PreparedTransfer(transferDate,
                        transferTime,
                        transferedPlayer,
                        transferAmount,
                        fromPlayer,
                        toPlayer));
            }
        }
        return result;
    }

    private String parseToPlayer(List<String> newsLineSingleWords, int toIndex) {
        String toPlayerName = "";
        for (int j = toIndex + 1; j < newsLineSingleWords.size(); j++) {
            toPlayerName = toPlayerName.concat(newsLineSingleWords.get(j));
        }
        return toPlayerName;
    }

    private String parseFromPlayer(List<String> newsLineSingleWords, int fromIndex, int toIndex) {
        String fromPlayerName = "";
        for (int i = fromIndex + 1; i <= toIndex - 1; i++) {
            fromPlayerName = fromPlayerName.concat(newsLineSingleWords.get(i));
        }
        return fromPlayerName;
    }

    private Long parseTransferAmount(List<String> newsLineSingleWords) {
        int forIndex = newsLineSingleWords.indexOf(FOR_PRICE);
        return parseNumber(newsLineSingleWords.get(forIndex + 1));
    }

    private String parseTransferedPlayer(List<String> newsLineSingleWords) {
        int wechseltIndex = newsLineSingleWords.indexOf(CHANGES_VERB);
        return newsLineSingleWords.get(wechseltIndex - 1);
    }

    private String parseTransferTime(List<String> singleNewsLine) {
        Pattern timePattern = Pattern.compile("\\d{2}:\\d{2}");
        return singleNewsLine.stream()
                .filter(s -> timePattern.matcher(s).matches())
                .findFirst()
                .orElse(DEFAULT_TRANSFER_TIME);
    }

    private Long parseNumber(String price) {
        NumberFormat nf = NumberFormat.getInstance();
        try {
            Number number = nf.parse(price);
            return number.longValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

}
