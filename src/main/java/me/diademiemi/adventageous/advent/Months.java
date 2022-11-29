package me.diademiemi.adventageous.advent;

import java.util.Calendar;
import java.util.Locale;

public enum Months {

    JANUARY(1,10),
    FEBRUARY(2,11),
    MARCH(3,12),

    APRIL(4,14),
    MAY(5,15),
    JUNE(6,16),

    JULY(7,28),
    AUGUST(8,29),
    SEPTEMBER(9,30),

    OCTOBER(10,32),
    NOVEMBER(11,33),
    DECEMBER(12,34);

    private int number;
    private int slot;

    Months(int number, int slot) {
        this.number = number;
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        Calendar cal = Calendar.getInstance();
        cal.clear(); // Prevent overflowing
        cal.set(Calendar.MONTH, number - 1);
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

}
