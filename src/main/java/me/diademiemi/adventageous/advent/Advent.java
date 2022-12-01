package me.diademiemi.adventageous.advent;

import org.bukkit.Material;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Advent {

    public static HashMap<Integer, Year> years = new HashMap<Integer, Year>();

    public static String offset = "+0";

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

    public static String getOffset() {
        return offset;
    }

    public static boolean setOffset(String offset) {
        try {
            // Get first character
            char firstChar = offset.charAt(0);
            if (firstChar == '+') {
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                // Add the number to the date as minutes
                Advent.offset = offset;
                return true;
            } else if (firstChar == '-') {
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                // Add the number to the date as minutes
                Advent.offset = offset;
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }

    public static Material getAvailableIcon() {
        return availableIcon;
    }

    public static void setAvailableIcon(Material availableIcon) {
        Advent.availableIcon = availableIcon;
    }

}
