package me.diademiemi.adventageous.advent;

import me.diademiemi.adventageous.lang.Message;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map map = new HashMap();

        if (claimedIcon != null) {
            map.put("claimedIcon", claimedIcon.name());
        } else {
            map.put("claimedIcon", null);
        }

        if (lockedIcon != null) {
            map.put("lockedIcon", lockedIcon.name());
        } else {
            map.put("lockedIcon", null);
        }
        map.put("date", date);
        map.put("name", name);
        map.put("rewards", rewards);
        map.put("hidden", hidden);
        map.put("playersClaimed", playersClaimed);

        return map;
    }

    public Day (Map<String, Object> map) {
        if (map.get("claimedIcon") != null) {
            claimedIcon = (Material) Material.valueOf(map.get("claimedIcon").toString());
        } else {
            claimedIcon = null;
        }
        if (map.get("lockedIcon") != null) {
            lockedIcon = (Material) Material.valueOf(map.get("lockedIcon").toString());
        } else {
            lockedIcon = null;
        }
        date = (int) map.get("date");
        name = (String) map.get("name");
        rewards = (ArrayList<ItemStack>) map.get("rewards");
        hidden = (boolean) map.get("hidden");
        playersClaimed = (ArrayList<String>) map.get("playersClaimed");
    }

    public Material claimedIcon;

    public Material lockedIcon;

    public Integer date;

    public String name;

    public ArrayList<ItemStack> rewards;

    public boolean hidden;

    public ArrayList<String> playersClaimed;

    public Day(Material claimedIcon, Material lockedIcon, int date, String name, ArrayList<ItemStack> rewards, boolean hidden, ArrayList<String> playersClaimed) {
        this.claimedIcon = claimedIcon;
        this.lockedIcon = lockedIcon;
        this.date = date;
        this.name = name;
        this.rewards = rewards;
        this.hidden = hidden;
        this.playersClaimed = playersClaimed;
    }

    public Day(int date) {
        this(Material.GREEN_SHULKER_BOX, null, date, "Day " + date, new ArrayList<>(), false, new ArrayList<>());
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

    public void toggleHidden() {
        this.hidden = !this.hidden;
    }

    public ArrayList<String> getPlayersClaimed() {
        return playersClaimed;
    }

    public void setPlayersClaimed(ArrayList<String> playersClaimed) {
        this.playersClaimed = playersClaimed;
    }

    public void addPlayerClaimed(String player) {
        this.playersClaimed.add(player);
    }

    public void removePlayerClaimed(String player) {
        this.playersClaimed.remove(player);
    }

    public boolean hasPlayerClaimed(String player) {
        return this.playersClaimed.contains(player);
    }

    public Boolean claim(Player player) {
        // Check if player has already claimed
        if (hasPlayerClaimed(player.getUniqueId().toString())) {
            Message.send(player, "already-claimed");
            return false;
        }
        // Check if player has enough space in inventory
        int emptySlots = 0;
        for (int slot = 0; slot < 36; slot++) {
            if (player.getInventory().getItem(slot) == null || player.getInventory().getItem(slot).getType() == Material.AIR) {
                emptySlots++;
            }
        }
        if (emptySlots < rewards.size()) {
            Message.send(player, "not-enough-space");
            return false;
        }

        // Give player rewards
        playersClaimed.add(player.getUniqueId().toString());
        for (ItemStack reward : rewards) {
            player.getInventory().addItem(reward);
        }

        // Play sound
        try {
            if (Advent.getClaimSound() != "NONE" && Sound.valueOf(Advent.getClaimSound()) != null) {
                player.playSound(player.getLocation(), Sound.valueOf(Advent.getClaimSound()), 1, 1);
            }
        } catch (Exception e) {
            // Do nothing
        }

        // Show particles
        try {
            if (Advent.getClaimParticle() != "NONE" && Particle.valueOf(Advent.getClaimParticle()) != null) {
                player.spawnParticle(Particle.valueOf(Advent.getClaimParticle()), player.getLocation().add(0, 1, 0), 100, 0.5, 0.5, 0.5);
            }
        } catch (Exception e) {
            // Do nothing
        }


        return true;

    }

}
