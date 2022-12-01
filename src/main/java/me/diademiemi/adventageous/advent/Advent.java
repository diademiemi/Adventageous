package me.diademiemi.adventageous.advent;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Advent {

    public static HashMap<Integer, Year> years = new HashMap<Integer, Year>();

    public static String offset = "+0";

    public static Material availableIcon = Material.LIME_SHULKER_BOX;

    public static String claimSound = "ENTITY_PLAYER_LEVELUP";

    public static String claimParticle = "VILLAGER_HAPPY";

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

    public static String getClaimSound() {
        return claimSound;
    }

    public static boolean setClaimSound(String claimSound) {
        if (claimSound.stripLeading().stripTrailing().equalsIgnoreCase("NONE")) {
            Advent.claimSound = "NONE";
            return true;
        }

        claimSound = claimSound.toUpperCase().stripLeading().stripTrailing();
        claimSound = claimSound.replace(".", "_");
        try {
            Sound.valueOf(claimSound);
            Advent.claimSound = claimSound;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getClaimParticle() {
        return claimParticle;
    }

    public static boolean setClaimParticle(String claimParticle) {
        if (claimParticle.stripLeading().stripTrailing().equalsIgnoreCase("NONE")) {
            Advent.claimParticle = "NONE";
            return true;
        }
        claimParticle = claimParticle.toUpperCase().stripLeading().stripTrailing();
        try {
            Particle.valueOf(claimParticle);
            Advent.claimParticle = claimParticle;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
