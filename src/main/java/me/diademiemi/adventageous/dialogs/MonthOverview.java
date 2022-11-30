package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.*;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Message;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MonthOverview implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        if (Advent.getYear(year) == null || Advent.getYear(year).getMonth(month - 1) == null) {
            Message.send(p, "no-active-month");
            return null;
        }
        Month monthObj = Advent.getYear(year).getMonth(month - 1);

        MenuBuilder builder = new MenuBuilder(monthObj.getName());
        builder.setSize(MenuSize.SIX_ROWS);
        builder.addButton(new GUIButton(), 45, 46, 47, 48, 50, 51, 52, 53);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("close")) {
            @Override
            public void onLeftClick(Player p) {
                close(p);
            }
        }, 49);

        int maxDays = monthObj.getDayCount();

        for (Days d : Days.values()) {
            if (d.getNumber() <= maxDays) {
                if (monthObj.getDay(d.getNumber() - 1) != null && !monthObj.getDay(d.getNumber() - 1).isHidden() && !monthObj.getDay(d.getNumber() - 1).getRewards().isEmpty()) {
                    Day dayObj = monthObj.getDay(d.getNumber() - 1);
                    if (dayObj.hasPlayerClaimed(p.getUniqueId().toString())) {
                        builder.addButton(new GUIButton(dayObj.getClaimedIcon(), 1, Button.get("already-claimed", "day", dayObj.getName())), d.getSlot());
                    } else if (day != d.getNumber()) {
                        Material lockedIcon = dayObj.getLockedIcon();
                        if (lockedIcon == null) {
                            lockedIcon = monthObj.getDefaultLockedIcon();
                        }
                        builder.addButton(new GUIButton(lockedIcon, 1, Button.get("locked-day", "day", dayObj.getName())), d.getSlot());
                    } else if (day == d.getNumber()) {
                        builder.addButton(new GUIButton(Advent.getAvailableIcon(), 1, Button.get("claim-day", "day", dayObj.getName())) {
                            @Override
                            public void onLeftClick(Player p) {
                                new ClaimDay().show(p, year, month, d.getNumber());
                            }
                        }, d.getSlot());
                    }

                }
            }
        }

        return builder.build(p);
    }

}
