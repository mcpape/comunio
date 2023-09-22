package com.example.comunio.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DateParser {

    private static final String TODAY_PREDICATE_WORD = "Heute";
    private static final String YESTERDAY_PREDICATE_WORD = "Gestern";

    private DateParser() {
    }

    public static Optional<LocalDate> parseToDate(String value) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        LocalDate localDateNow = LocalDate.now();
        LocalDate localDateYesterday = localDateNow.minusDays(1);

        Pattern datePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{2}");

        Predicate<String> todayDatePredicate = x -> x.equals(TODAY_PREDICATE_WORD);
        Predicate<String> yesterdayDatePredicate = x -> x.equals(YESTERDAY_PREDICATE_WORD);

        LocalDate date;
        if (todayDatePredicate.test(value)) {
            date = localDateNow;
        } else if (yesterdayDatePredicate.test(value)) {
            date = localDateYesterday;
        } else if (datePattern.matcher(value).matches()) {
            date = LocalDate.parse(value, dateFormatter);
        } else {
            return Optional.empty();
        }

        return Optional.of(date);
    }
}
