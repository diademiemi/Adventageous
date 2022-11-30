package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Day;
import me.diademiemi.adventageous.gui.input.Input;
import org.bukkit.entity.Player;

public class AdminIptSetDayName extends Input {
    public AdminIptSetDayName(Player player, Object... args) {
        super(player, args);
    }

    @Override
    public void action(String input) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        if (input != null && !input.equalsIgnoreCase("cancel")) {
            Advent.getYear(year).getMonth(month - 1).getDay(day - 1).setName(input);
        }

        new AdminModifyDay().show(player, year, month, day);

    }
}

