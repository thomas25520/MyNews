package com.mynews.data.enums;

/**
 * Created by Dutru Thomas on 10/06/2019.
 */
public enum PeriodType {
    one(1),
    seven(7),
    thirty(30);

    private int period;

    PeriodType(int period) {
        this.period = period;
    }

    public int toInt() {
        return period;
    }
}