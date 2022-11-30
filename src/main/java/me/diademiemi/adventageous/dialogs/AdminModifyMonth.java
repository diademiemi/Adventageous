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
import org.bukkit.inventory.ItemStack;

public class AdminModifyMonth implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-month-config", "year", Integer.toString(year) , "month", Integer.toString(month)));
        builder.setSize(MenuSize.SIX_ROWS);
        builder.addButton(new GUIButton(), 45, 46, 48, 50, 52, 53);

        Month monthObj = Advent.getYear(year).getMonth(month - 1);

        builder.addButton(new GUIButton(Material.NAME_TAG, 1, Button.get("admin-day-set-custom-name", "name", monthObj.getName())) {
            @Override
            public void onLeftClick(Player p) {
                close(p);
                new AdminIptSetMonthName(p, year, month);
            }
        }, 47);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyYear().show(p, year);
            }
        }, 49);

        builder.addButton(new GUIButton(monthObj.getDefaultLockedIcon(), 1, Button.get("admin-set-default-locked-icon")) {
            @Override
            public void onItemDrag(Player p, ItemStack is) {
                monthObj.setDefaultLockedIcon(is.getType());
                show(p, year, month);
            }
        }, 51);

        int maxDays = monthObj.getDayCount();

        for (Days d : Days.values()) {
            if (d.getNumber() <= maxDays) {
                if (monthObj.getDay(d.getNumber() - 1) != null) {
                    builder.addButton(new GUIButton(monthObj.getDay(d.getNumber() - 1).getClaimedIcon(), 1, Button.get("admin-configure-day", "day", Integer.toString(d.getNumber()), "hidden", Boolean.toString(monthObj.getDay(d.getNumber() - 1).isHidden()))) {
                        @Override
                        public void onLeftClick(Player p) {
                            new AdminModifyDay().show(p, year, month, d.getNumber());
                        }

                        @Override
                        public void onRightClick(Player p) {
                            monthObj.getDay(d.getNumber() - 1).toggleHidden();
                            new AdminModifyMonth().show(p, year, month);
                        }
                    }, d.getSlot());
                }
            }
        }

        return builder.build(p);
    }

}
