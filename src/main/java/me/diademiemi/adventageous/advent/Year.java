package me.diademiemi.adventageous.advent;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
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
    private ArrayList<Month> months;

    public Year(Map<String, Object> map) {
        this((int) map.get("year"), (ArrayList<Month>) map.get("months"));
    }

    public Year(int year) {
        this(year, null);

        this.months = new ArrayList<Month>(12);

        for (int i = 0; i < 12; i++) {
            this.months.add(null);
        }
    }
    public Year(int year, ArrayList<Month> months) {
        this.year = year;
        this.months = months;
        Advent.addYear(this);
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Month> getMonths() {
        return months;
    }

    public Month getMonth(int month) {
        return months.get(month);
    }

    public void setMonth(int month, Month monthObj) {
        months.set(month, monthObj);
    }

}
