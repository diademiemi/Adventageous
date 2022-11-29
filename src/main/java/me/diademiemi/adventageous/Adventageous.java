package me.diademiemi.adventageous;

import me.diademiemi.adventageous.advent.Day;
import me.diademiemi.adventageous.advent.Month;
import me.diademiemi.adventageous.command.CommandHandler;
import me.diademiemi.adventageous.gui.GUIListener;
import me.diademiemi.adventageous.gui.input.InputListener;
import me.diademiemi.adventageous.lang.Lang;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Adventageous extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(Day.class);
        ConfigurationSerialization.registerClass(Month.class);
    }

    public static Adventageous plugin;

    private static String pluginName = "Adventageous";

    private static PluginManager pm;

    private static String[] permissions = new String[]{
            "help",
            "advent",
            "admin",
    };

    @Override
    public void onEnable() {

        plugin = this;
        // Plugin startup logic
        pm = getServer().getPluginManager();

        Lang.init();
        Lang.loadConfig();


        for (String permission : permissions) {
            pm.addPermission(new org.bukkit.permissions.Permission(pluginName.toLowerCase() + "." + permission));
        }

        pm.registerEvents(new GUIListener(), this);
        pm.registerEvents(new InputListener(), this);

        getCommand("adventageous").setExecutor(new CommandHandler());
        getCommand("advent").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Adventageous getPlugin() {
        return plugin;
    }
}
