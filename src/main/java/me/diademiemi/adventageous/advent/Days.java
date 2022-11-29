package me.diademiemi.adventageous.advent;

import java.util.HashMap;

public enum Days {

    DAY1(1, "1st", 1),
    DAY2(2, "2nd", 2),
    DAY3(3, "3rd", 3),
    DAY4(4, "4th", 4),
    DAY5(5, "5th", 5),
    DAY6(6, "6th", 6),
    DAY7(7, "7th", 7),

    DAY8(8, "8th", 10),
    DAY9(9, "9th", 11),
    DAY10(10, "10th", 12),
    DAY11(11, "11th", 13),
    DAY12(12, "12th", 14),
    DAY13(13, "13th", 15),
    DAY14(14, "14th", 16),

    DAY15(15, "15th", 19),
    DAY16(16, "16th", 20),
    DAY17(17, "17th", 21),
    DAY18(18, "18th", 22),
    DAY19(19, "19th", 23),
    DAY20(20, "20th", 24),
    DAY21(21, "21st", 25),

    DAY22(22, "22nd", 28),
    DAY23(23, "23rd", 29),
    DAY24(24 , "24th", 30),
    DAY25(25, "25th", 31),
    DAY26(26, "26th", 32),
    DAY27(27, "27th", 33),
    DAY28(28, "28th", 34),

    DAY29(29, "29th", 37),
    DAY30(30, "30th", 38),
    DAY31(31, "31st", 39);

    private int number;

    private String ordinal;

    private int slot;

    Days(int number, String ordinal, int slot) {
        this.number = number;
        this.ordinal = ordinal;
        this.slot = slot;
    }

    public int getNumber() {
        return number;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public int getSlot() {
        return slot;
    }

}
