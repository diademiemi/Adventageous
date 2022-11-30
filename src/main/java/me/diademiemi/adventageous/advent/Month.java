package me.diademiemi.adventageous.advent;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class Month implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map map = new HashMap();
        map.put("days", days);
        map.put("number", number);
        map.put("name", name);
        map.put("defaultLockedIcon", defaultLockedIcon.name());

        return map;
    }

    public Month(Map<String, Object> map) {
        days = (HashMap<Integer, Day>) map.get("days");
        number = (int) map.get("number");
        name = (String) map.get("name");
        defaultLockedIcon = (Material) Material.valueOf(map.get("defaultLockedIcon").toString());
    }

    public HashMap<Integer, Day> days;

    public int number;

    public String name;

    public Material defaultLockedIcon;

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
        this.defaultLockedIcon = Material.GRAY_SHULKER_BOX;
    }

    public Month(int month, HashMap<Integer, Day> days, String name, Material defaultLockedIcon) {
        this.number = month;
        this.days = days;
        this.name = name;
        this.defaultLockedIcon = defaultLockedIcon;
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

    public void setName(String name) {
        this.name = name;
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

    public Material getDefaultLockedIcon() {
        return defaultLockedIcon;
    }

    public void setDefaultLockedIcon(Material defaultLockedIcon) {
        this.defaultLockedIcon = defaultLockedIcon;
    }


}
