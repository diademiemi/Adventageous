package me.diademiemi.adventageous.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class GUI {
    public static HashMap<Player, GUI> guiMap = new HashMap<Player, GUI>();
    public HashMap<Integer, GUIButton> buttons;
    public Inventory inventory;
    public Player player;
    public String title;

    /**
     * @param gui GUI to add
     */
    public void addGUI(GUI gui) {
        guiMap.put(player, gui);
    }

    /**
     * @param gui GUI to remove
     */
    public void removeGUI(GUI gui) {
        guiMap.remove(player, gui);
    }

    /**
     * @return all loaded GUIs
     */
    public static HashMap<Player, GUI> getGUIMap() {
        return guiMap;
    }

    /**
     * @param player
     * @return the GUI of the given inventory and player
     */
    public static GUI getGUI(Player player) {
        return guiMap.get(player);
    }

    public void open() {
        player.openInventory(inventory);
    }

    public void close() {
        removeGUI(this);
        player.closeInventory();
    }

    public GUIButton getButton(int slot) {
        return buttons.get(slot);
    }

    public void bakeButtons() {
        for (Map.Entry<Integer, GUIButton> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }
    }

    public void updateButton(int slot, GUIButton button) throws IndexOutOfBoundsException {
        if (slot >= inventory.getSize()) {
            throw new IndexOutOfBoundsException("Slot is out of bounds");
        } else {
            buttons.put(slot, button);
            inventory.setItem(slot, button.getItemStack());
        }
    }

}
