package me.diademiemi.adventageous;

import me.diademiemi.adventageous.advent.Advent;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AdventIO {

    private static File configFile;

    private static YamlConfiguration config;

    public static void init() {
        configFile = new File(Adventageous.getPlugin().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            Adventageous.getPlugin().saveResource("config.yml", false);
        }
    }

    public static void writeConfig() {
        config.set("years", Advent.getYears());
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
