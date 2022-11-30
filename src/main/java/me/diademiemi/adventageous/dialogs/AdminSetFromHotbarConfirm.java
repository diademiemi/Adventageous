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

public class AdminSetFromHotbarConfirm implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        Day dayObj = Advent.getYear(year).getMonth(month - 1).getDay(day - 1);

        MenuBuilder builder = new MenuBuilder(Title.get("admin-confirm-set-hotbar"));
        builder.setSize(MenuSize.TWO_ROWS);
        builder.addButton(new GUIButton(), 9, 10, 11, 13, 15, 16, 17);

        for (int i = 0; i < 9; i++) {
            if (p.getInventory().getItem(i) != null) {
                builder.addButton(new GUIButton(p.getInventory().getItem(i)), i);
            }
        }

        builder.addButton(new GUIButton(Material.RED_SHULKER_BOX, 1, Button.get("admin-cancel")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyDay().show(p, year, month, day);
            }
        }, 12);

        builder.addButton(new GUIButton(Material.LIME_SHULKER_BOX, 1, Button.get("admin-confirm")) {
            @Override
            public void onLeftClick(Player p) {
                dayObj.setRewardsFromHotbar(p);
                new AdminModifyDay().show(p, year, month, day);
            }
        }, 14);

        return builder.build(p);
    }

}
