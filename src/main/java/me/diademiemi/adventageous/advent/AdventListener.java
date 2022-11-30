package me.diademiemi.adventageous.advent;

import me.diademiemi.adventageous.Adventageous;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;

public class AdventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        Day dayObj;
        try {
            dayObj = Advent.getYear(year).getMonth(month - 1).getDay(day - 1);
        } catch (NullPointerException e) {
            // Do nothing
            return;
        }
        if (player.hasPermission("adventageous.advent")) {
            if (!dayObj.hasPlayerClaimed(player.getUniqueId().toString())) {
                // Delay message for 80 ticks to ensure player has loaded
                Adventageous.getPlugin().getServer().getScheduler().runTaskLater(Adventageous.getPlugin(), () -> {
                        Message.send(player, "join-today-available");
                }, 80L );
            }
        }
    }
}
