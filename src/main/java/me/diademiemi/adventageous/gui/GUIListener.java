package me.diademiemi.adventageous.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        GUI gui = GUI.getGUI(player);
        if (gui != null) {
            if (e.getClickedInventory() == gui.getInventory()) e.setCancelled(true);
            if (gui.getButton(e.getRawSlot()) != null) {
                if (e.getCursor().getType() != Material.AIR) {
                    gui.getButton(e.getRawSlot()).onItemDrag(player, e.getCursor());
                } else if (e.isLeftClick()) {
                    gui.getButton(e.getRawSlot()).onLeftClick(player);
                } else if (e.isRightClick()) {
                    gui.getButton(e.getRawSlot()).onRightClick(player);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        GUI gui = GUI.getGUI((Player) e.getPlayer());
        if (gui != null) {
            gui.close();
        }
    }

    @EventHandler
    public void onPlayerQUit(PlayerQuitEvent e) {
        GUI gui = GUI.getGUI(e.getPlayer());
        if (gui != null) {
            gui.close();
        }
    }
}
