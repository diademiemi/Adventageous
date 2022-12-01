package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.gui.input.Input;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;

public class AdminIptSetOffset extends Input {
    public AdminIptSetOffset(Player player, Object... args) {
        super(player, "admin-enter-offset", args);
    }

    @Override
    public void action(String input) {
        if (input != null && !input.equalsIgnoreCase("cancel")) {
            if (Advent.setOffset(input)) {
                Message.send(player, "admin-offset-set", "offset", Advent.getOffset());
            } else {
                Message.send(player, "admin-invalid-offset");
            }
        }
        new AdminMenu().show(player);

    }
}

