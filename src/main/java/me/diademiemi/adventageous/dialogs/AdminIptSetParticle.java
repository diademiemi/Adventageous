package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.gui.input.Input;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;

public class AdminIptSetParticle extends Input {
    public AdminIptSetParticle(Player player, Object... args) {
        super(player, "admin-enter-particle", args);
    }

    @Override
    public void action(String input) {
        if (input != null && !input.equalsIgnoreCase("cancel")) {
            if (Advent.setClaimParticle(input)) {
                Message.send(player, "admin-particle-set", "particle", Advent.getClaimParticle());
            } else {
                Message.send(player, "admin-invalid-particle");
            }
        }
        new AdminMenu().show(player);

    }
}

