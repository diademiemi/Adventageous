package me.diademiemi.adventageous.advent;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class Year implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("months", months);
        return map;
    }

    private int year;
    private Month[] months;

    public Year(Map<String, Object> map) {
        this((int) map.get("year"), (Month[]) map.get("months"));
    }

    public Year(int year) {
        this(year, null);

        months = new Month[12];

        for (int i = 0; i < 12; i++) {
            months[i] = null;
        }
    }
    public Year(int year, Month[] months) {
        this.year = year;
        this.months = months;
        Advent.addYear(this);
    }

    public int getYear() {
        return year;
    }

    public Month[] getMonths() {
        return months;
    }

    public Month getMonth(int month) {
        return months[month];
    }

    public void setMonth(int month, Month monthObj) {
        months[month] = monthObj;
    }

}
