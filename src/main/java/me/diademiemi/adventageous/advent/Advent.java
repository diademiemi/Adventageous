package me.diademiemi.adventageous.advent;

import org.bukkit.Material;

import java.util.HashMap;

public class Advent {

    public static HashMap<Integer, Year> years = new HashMap<Integer, Year>();

    public static Material availableIcon = Material.LIME_SHULKER_BOX;

    public static void addYear(Year year) {
        years.put(year.getYear(), year);
    }

    public static Year getYear(int year) {
        return years.get(year);
    }

    public static void removeYear(int year) {
        years.remove(year);
    }

    public static void removeYear(Year year) {
        years.remove(year.getYear());
    }

    public static HashMap<Integer, Year> getYears() {
        return years;
    }

    public static Material getAvailableIcon() {
        return availableIcon;
    }

    public static void setAvailableIcon(Material availableIcon) {
        Advent.availableIcon = availableIcon;
    }

}
