package me.diademiemi.adventageous.gui.input;

import me.diademiemi.adventageous.Adventageous;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class InputListener implements Listener {
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if (Input.getInputs().containsKey(e.getPlayer())) {
            Adventageous.getPlugin().getServer().getScheduler()
                .runTask(Adventageous.getPlugin(), Runnable -> {
                    Input.getInputs().get(e.getPlayer()).onInput(e.getMessage());
                });
            e.setCancelled(true);
        }
    }

}
