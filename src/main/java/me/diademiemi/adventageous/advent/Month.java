package me.diademiemi.adventageous.advent;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class Month implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map map = new HashMap();

        map.put("days" , days);
        map.put("name", name);

        return map;
    }

    public Month(Map<String, Object> map) {
        days = (HashMap<Integer, Day>) map.get("days");
        name = (String) map.get("name");
    }

    public HashMap<Integer, Day> days;

    public int number;

    public String name;

    public Month(int year, int month) {
        // Populate days with how many days are in the month

        Calendar cal = Calendar.getInstance();
        cal.clear(); // Prevent overflowing
        cal.set(Calendar.YEAR, year - 1);
        cal.set(Calendar.MONTH, month - 1);

        this.number = month;

        this.days = new HashMap<>();
        int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Account for leap years
        if (year % 4 == 0) {
            if (month == 2) {
                maxDays += 1;
            }
        }

        for (int i = 0; i < maxDays; i++) {
            days.put(i , new Day(i + 1));
        }
        this.name = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public Month(int month, HashMap<Integer, Day> days, String name) {
        this.number = month;
        this.days = days;
        this.name = name;
    }

    public Day getDay(int day) {
        return days.get(day);
    }

    public void setDay(int day, Day d) {
        days.put(day, d);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getDayCount() {
        return days.size();
    }

    public HashMap<Integer, Day> getDays() {
        return days;
    }


}
