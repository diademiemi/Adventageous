package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Day;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.GUIListener;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClaimDay implements Dialog {


    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-day-config", "month", Integer.toString(month), "day", Integer.toString(day)));
        builder.setSize(MenuSize.TWO_ROWS);
        builder.addButton(new GUIButton(), 9, 10, 12, 13, 14, 16, 17);

        Day dayObj = Advent.getYear(year).getMonth(month - 1).getDay(day - 1);

        for (int i = 0; i < 9; i++) {

            if (i < dayObj.getRewards().size() && dayObj.getRewards().get(i) != null) {
                int finalI = i;
                builder.addButton(new GUIButton(dayObj.getRewards().get(finalI)) {
                }, i);
            }
        }

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new MonthOverview().show(p, year, month, day);
            }
        }, 11);

        builder.addButton(new GUIButton(Material.CHEST, 1, Button.get("claim-items")) {
            @Override
            public void onLeftClick(Player p) {
                dayObj.claim(p);
                new MonthOverview().show(p, year, month, day);
            }
        }, 15);

        return builder.build(p);
    }

}
