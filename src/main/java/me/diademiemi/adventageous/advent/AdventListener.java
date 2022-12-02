package me.diademiemi.adventageous.advent;

import me.diademiemi.adventageous.Adventageous;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LocalDateTime date = LocalDateTime.now();
        if (Advent.getOffset() != null && Advent.getOffset() != "-0" && Advent.getOffset() != "+0") {
            String offset = Advent.getOffset();
            // Get first character
            char firstChar = offset.charAt(0);
            if (firstChar == '+') {
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                // Add the number to the date as minutes
                date = date.plusMinutes(number);
            } else if (firstChar == '-') {
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                // Add the number to the date as minutes
                date = date.minusMinutes(number);
            }
        }
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
