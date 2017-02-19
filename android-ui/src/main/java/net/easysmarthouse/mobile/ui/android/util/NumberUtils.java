package net.easysmarthouse.mobile.ui.android.util;

import java.text.DecimalFormat;

/**
 * Created by rusakovich on 26.01.2017.
 */
public class NumberUtils {

    private static final int DOUBLE_FORMAT_DEF_DIGITS = 2;

    private NumberUtils(){
    }

    public static String getFormattedSimple(double dbl){
        return getFormatted(dbl, DOUBLE_FORMAT_DEF_DIGITS);
    }

    public static String getFormatted(double dbl, int numbersCount){
        StringBuilder formatBuilder = new StringBuilder("#.");
        for (int i = 0; i < numbersCount; i++) {
            formatBuilder.append("0");
        }
        DecimalFormat df = new DecimalFormat(formatBuilder.toString());
        return df.format(dbl);
    }
}
