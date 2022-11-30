package me.diademiemi.adventageous.advent;

import me.diademiemi.adventageous.lang.Message;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Day implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map map = new HashMap();

        map.put("icon" , claimedIcon);
        map.put("date", date);
        map.put("name", name);
        map.put("rewards", rewards);

        return map;
    }

    public Day (Map<String, Object> map) {
        claimedIcon = (Material) map.get("icon");
        date = (int) map.get("date");
        name = (String) map.get("name");
        rewards = (ArrayList<ItemStack>) map.get("rewards");
    }

    public Material claimedIcon;

    public Material lockedIcon;

    public Integer date;

    public String name;

    public ArrayList<ItemStack> rewards;

    public boolean hidden;

    public ArrayList<UUID> playersClaimed;

    public Day(Material claimedIcon, Material lockedIcon, int date, String name, ArrayList<ItemStack> rewards, boolean hidden, ArrayList<UUID> playersClaimed) {
        this.claimedIcon = claimedIcon;
        this.lockedIcon = lockedIcon;
        this.date = date;
        this.name = name;
        this.rewards = rewards;
        this.hidden = hidden;
        this.playersClaimed = playersClaimed;
    }

    public Day(int date) {
        this(Material.GREEN_SHULKER_BOX, Material.GRAY_SHULKER_BOX, date, "Day " + date, new ArrayList<>(), false, new ArrayList<>());
    }

    public Material getClaimedIcon() {
        return claimedIcon;
    }

    public void setClaimedIconFromRewards() {
        if (rewards.size() > 0 && rewards.get(0) != null) {
            claimedIcon = rewards.get(0).getType();
        }
    }


    public void setClaimedIcon(Material claimedIcon) {
        this.claimedIcon = claimedIcon;
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
        setClaimedIconFromRewards();
    }

    public void setReward(int index, ItemStack reward) {
        this.rewards.set(index, reward);
        setClaimedIconFromRewards();
    }

    public void addReward(ItemStack reward) {
        this.rewards.add(reward);
        setClaimedIconFromRewards();
    }

    public void removeReward(int index) {
        this.rewards.remove(index);
    }

    public void setRewardsFromHotbar(Player player) {
        ArrayList<ItemStack> rewards = new ArrayList<ItemStack>();
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null) {
                rewards.add(player.getInventory().getItem(i).clone());
            }
        }
        this.rewards = rewards;
        setClaimedIconFromRewards();
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public ArrayList<UUID> getPlayersClaimed() {
        return playersClaimed;
    }

    public void setPlayersClaimed(ArrayList<UUID> playersClaimed) {
        this.playersClaimed = playersClaimed;
    }

    public void addPlayerClaimed(UUID player) {
        this.playersClaimed.add(player);
    }

    public void removePlayerClaimed(UUID player) {
        this.playersClaimed.remove(player);
    }

    public boolean hasPlayerClaimed(UUID player) {
        return this.playersClaimed.contains(player);
    }

    public void claim(Player player) {
        // Check if player has already claimed
        if (hasPlayerClaimed(player.getUniqueId())) {
            Message.send(player, "already-claimed");
            return;
        }
        // Check if player has enough space in inventory
        int emptySlots = 0;
        for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
            if (player.getInventory().getItem(slot) == null || player.getInventory().getItem(slot).getType() == Material.AIR) {
                emptySlots++;
            }
        }
        if (emptySlots < rewards.size()) {
            Message.send(player, "not-enough-space");
            return;
        }

        // Give player rewards
        playersClaimed.add(player.getUniqueId());
        for (ItemStack reward : rewards) {
            player.getInventory().addItem(reward);
        }

    }

}
