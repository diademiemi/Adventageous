package me.diademiemi.adventageous;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Year;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
