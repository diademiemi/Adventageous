package me.diademiemi.adventageous.gui;

import me.diademiemi.adventageous.gui.menu.Menu;
import org.bukkit.entity.Player;

public interface Dialog {
    default void show(Player player, Object... args) {
        try {
            close(player);
        } catch (NullPointerException e) {
            // Do nothing
        }

        Menu menu = create(player, args);
        if (menu != null) {
            menu.open(); // Sometimes it is intended to return null
        }
    }

    default void close(Player player) {
        GUI.getGUI(player).close();
    }

    Menu create(Player p, Object... args);
}
