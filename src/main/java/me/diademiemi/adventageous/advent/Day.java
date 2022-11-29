package me.diademiemi.adventageous.advent;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Day implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map map = new HashMap();

        map.put("icon" , icon);
        map.put("date", date);
        map.put("name", name);
        map.put("rewards", rewards);

        return map;
    }

    public Day (Map<String, Object> map) {
        icon = (Material) map.get("icon");
        date = (int) map.get("date");
        name = (String) map.get("name");
        rewards = (ArrayList<ItemStack>) map.get("rewards");
    }

    public Material icon;

    public Material lockedIcon;

    public Integer date;

    public String name;

    public ArrayList<ItemStack> rewards;

    public Day(Material icon, Material lockedIcon, int date, String name, ArrayList<ItemStack> rewards) {
        this.icon = icon;
        this.lockedIcon = lockedIcon;
        this.date = date;
        this.name = name;
        this.rewards = rewards;
    }

    public Day(int date) {
        this(Material.BLACK_STAINED_GLASS_PANE, null, date, "Day " + date, null);
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public Material getLockedIcon() {
        return lockedIcon;
    }

    public void setLockedIcon(Material lockedIcon) {
        this.lockedIcon = lockedIcon;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<ItemStack> rewards) {
        this.rewards = rewards;
    }

    public void setRewardsFromHotbar(Player player) {
        ArrayList<ItemStack> rewards = new ArrayList<ItemStack>();
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null) {
                rewards.add(player.getInventory().getItem(i));
            }
        }
        this.rewards = rewards;
    }


}
