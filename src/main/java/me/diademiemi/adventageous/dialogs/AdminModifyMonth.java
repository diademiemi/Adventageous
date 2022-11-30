package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.*;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AdminModifyMonth implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-month-config", "year", Integer.toString(year) , "month", Integer.toString(month)));
        builder.setSize(MenuSize.SIX_ROWS);
        builder.addButton(new GUIButton(), 45, 46, 47, 48, 50, 51, 52, 53);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyYear().show(p, year);
            }
        }, 49);

        Month monthObj = Advent.getYear(year).getMonth(month - 1);

        int maxDays = monthObj.getDayCount();

        for (Days d : Days.values()) {
            if (d.getNumber() <= maxDays) {
                if (monthObj.getDay(d.getNumber() - 1) != null) {
                    builder.addButton(new GUIButton(monthObj.getDay(d.getNumber() - 1).getClaimedIcon(), 1, Button.get("admin-configure-day", "day", Integer.toString(d.getNumber()))) {
                        @Override
                        public void onLeftClick(Player p) {
                            new AdminModifyDay().show(p, year, month, d.getNumber());
                        }
                    }, d.getSlot());
                }
            }
        }

        return builder.build(p);
    }

}
