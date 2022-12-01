package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.gui.input.Input;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;

public class AdminIptSetSound extends Input {
    public AdminIptSetSound(Player player, Object... args) {
        super(player, "admin-enter-sound", args);
    }

    @Override
    public void action(String input) {
        if (input != null && !input.equalsIgnoreCase("cancel")) {
            if (Advent.setClaimSound(input)) {
                Message.send(player, "admin-sound-set", "sound", Advent.getClaimSound());
            } else {
                Message.send(player, "admin-invalid-sound");
            }
        }
        new AdminMenu().show(player);

    }
}

