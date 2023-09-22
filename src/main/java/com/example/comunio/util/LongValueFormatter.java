package com.example.comunio.util;

import java.text.NumberFormat;
import java.util.Locale;

public class LongValueFormatter {

    private static final NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);

    private LongValueFormatter() {
    }

    public static String format(Long value) {
        return nf.format(value);
    }

}
