package com.mynews.utils;

/**
 * Created by Dutru Thomas on 24/07/2019.
 */
public class DateFormatter {
    public String getDisplayDateFormat(int year, int month, int day) {
        return String.format("%02d", day) + "/" + String.format("%02d", month + 1) + "/" + year;
    }

    // Why Month + 1 ? : In docs adroid, month begin at 0, day begin at 1
    public String getApiDateFormat(int year, int month, int day) {
        return year + String.format("%02d", month + 1) + String.format("%02d", day);
    }
}
