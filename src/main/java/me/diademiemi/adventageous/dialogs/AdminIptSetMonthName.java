package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.gui.input.Input;
import org.bukkit.entity.Player;

public class AdminIptSetMonthName extends Input {
    public AdminIptSetMonthName(Player player, Object... args) {
        super(player, args);
    }

    @Override
    public void action(String input) {
        int year = (int) args[0];
        int month = (int) args[1];

        if (input != null && !input.equalsIgnoreCase("cancel")) {
            Advent.getYear(year).getMonth(month - 1).setName(input);
        }

        new AdminModifyMonth().show(player, year, month);

    }
}

