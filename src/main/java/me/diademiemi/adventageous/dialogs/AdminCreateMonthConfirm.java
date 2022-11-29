package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Month;
import me.diademiemi.adventageous.advent.Year;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AdminCreateMonthConfirm implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-create-month", "year", Integer.toString(year) , "month", Integer.toString(month)));
        builder.setSize(MenuSize.HALF_ROW);
        builder.addButton(new GUIButton(Material.LIME_SHULKER_BOX, 1, Button.get("admin-confirm")) {
            @Override
            public void onLeftClick(Player p) {
                Year thisYear = Advent.getYear(year);

                if (thisYear == null) {
                    thisYear = new Year(year);
                }

                thisYear.setMonth(month - 1, new Month(year, month));

                new AdminYearOverview().show(p, year);
            }
        }, 1);
        builder.addButton(new GUIButton(Material.RED_SHULKER_BOX, 1, Button.get("admin-cancel")) {
            @Override
            public void onLeftClick(Player p) {

                new AdminYearOverview().show(p, year);
            }
        }, 3);

        return builder.build(p);
    }

}
