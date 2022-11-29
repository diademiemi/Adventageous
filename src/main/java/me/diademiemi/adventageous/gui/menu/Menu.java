package me.diademiemi.adventageous.gui.menu;

import me.diademiemi.adventageous.gui.GUI;
import me.diademiemi.adventageous.gui.GUIButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;

public class Menu extends GUI {
    private MenuSize size;

    /**
     * Create menu
     * @param size the size of the GUI
     * @param title the title of the GUI
     * @param player the Player for the GUI
     */
    public Menu(Player player, MenuSize size, String title, HashMap<Integer, GUIButton> buttons) {
        this.size = size;
        this.title = title;
        this.player = player;
        this.buttons = buttons;

        if (this.size == MenuSize.HALF_ROW) {
            this.inventory = Bukkit.getServer().createInventory(null, InventoryType.HOPPER, title);
        } else {
            this.inventory = Bukkit.getServer().createInventory(null, this.size.getSize(), title);
        }

        this.bakeButtons();
        guiMap.put(player, this);
    }

}
