package me.diademiemi.adventageous;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Year;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AdventIO {

    private static File dataFile;

    private static YamlConfiguration data;

    public static void init() {
        dataFile = new File(Adventageous.getPlugin().getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            Adventageous.getPlugin().saveResource("data.yml", false);
        }
    }

    public static void writeConfig() {
        data.set("years", Advent.getYears());
        data.set("availableIcon", Advent.getAvailableIcon().name());
        data.set("initialised", true);
        data.set("offset", Advent.getOffset());
        data.set("claimSound", Advent.getClaimSound());
        data.set("claimParticle", Advent.getClaimParticle());
        data.set("sendDailyReminder", Advent.getSendDailyReminder());
        try {
            data.save(dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        data = YamlConfiguration.loadConfiguration(dataFile);
        if (data.getBoolean("initialised")) {
            try {
                Advent.setAvailableIcon(Material.valueOf(data.getString("availableIcon")));
                for (String key : data.getConfigurationSection("years").getKeys(false)) {
                    Advent.addYear((Year) data.get("years." + key));
                }
                if (data.getString("offset") != null) {
                    Advent.setOffset(data.getString("offset"));
                }
                if (data.getString("claimSound") != null) {
                    Advent.setClaimSound(data.getString("claimSound"));
                }
                if (data.getString("claimParticle") != null) {
                    Advent.setClaimParticle(data.getString("claimParticle"));
                }
                if (data.getString("sendDailyReminder") != null) {
                    Advent.setSendDailyReminder(data.getBoolean("sendDailyReminder"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
