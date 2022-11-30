package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Day;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AdminClearPlayersConfirm implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        Day dayObj = Advent.getYear(year).getMonth(month - 1).getDay(day - 1);

        MenuBuilder builder = new MenuBuilder(Title.get("admin-confirm-clear-players", "year", Integer.toString(year), "month", Integer.toString(month), "day", Integer.toString(day)));
        builder.setSize(MenuSize.HALF_ROW);
        builder.addButton(new GUIButton(), 0);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS, 1, Button.get("admin-cancel")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyDay().show(p, year, month, day);
            }
        }, 1);

        builder.addButton(new GUIButton(Material.PAPER, 1, Button.get("admin-notice-clear-players")), 2);

        builder.addButton(new GUIButton(Material.LIME_STAINED_GLASS, 1, Button.get("admin-confirm")) {
            @Override
            public void onLeftClick(Player p) {
                dayObj.setPlayersClaimed(new ArrayList<>());
                new AdminModifyDay().show(p, year, month, day);
            }
        }, 3);

        builder.addButton(new GUIButton(Material.YELLOW_STAINED_GLASS, 1, Button.get("admin-clear-me")) {
            @Override
            public void onLeftClick(Player p) {
                dayObj.removePlayerClaimed(p.getUniqueId().toString());
                new AdminModifyDay().show(p, year, month, day);
            }
        }, 4);

        return builder.build(p);
    }

}
