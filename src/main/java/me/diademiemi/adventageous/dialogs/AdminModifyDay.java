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
import org.bukkit.inventory.ItemStack;

public class AdminModifyDay implements Dialog {


    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-day-config", "month", Integer.toString(month), "day", Integer.toString(day)));
        builder.setSize(MenuSize.TWO_ROWS);
        builder.addButton(new GUIButton(), 12, 14);

        Day dayObj = Advent.getYear(year).getMonth(month - 1).getDay(day - 1);

        for (int i = 0; i < 9; i++) {

            if (i < dayObj.getRewards().size() && dayObj.getRewards().get(i) != null) {
                int finalI = i;
                builder.addButton(new GUIButton(dayObj.getRewards().get(finalI)) {
                    @Override
                    public void onItemDrag(Player p, ItemStack is) {
                        if (is != null) {
                            dayObj.setReward(finalI, is.clone());
                            new AdminModifyDay().show(p, year, month, day);
                        }
                    }

                    @Override
                    public void onRightClick(Player p) {
                        dayObj.removeReward(finalI);
                        new AdminModifyDay().show(p, year, month, day);
                    }
                }, i);
            } else {
                builder.addButton(new GUIButton(Material.WHITE_STAINED_GLASS_PANE, 1, Button.get("admin-empty-slot")) {
                    @Override
                    public void onItemDrag(Player p, ItemStack is) {
                        if (is != null) {
                            dayObj.addReward(is.clone());
                            new AdminModifyDay().show(p, year, month, day);
                        }
                    }
                }, i);
            }
        }

        builder.addButton(new GUIButton(Material.CLOCK, day, Button.get("admin-open-month-as-day", "year", Integer.toString(year), "month", Integer.toString(month), "day", Integer.toString(day))) {
            @Override
            public void onLeftClick(Player p) {
                new MonthOverview().show(p, year, month, day);
            }
        }, 9);

        builder.addButton(new GUIButton(Material.PAPER, 1, Button.get("admin-explain-rightclick-delete")), 10);

        builder.addButton(new GUIButton(Material.CHEST, 1, Button.get("admin-quick-set-hotbar")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminSetFromHotbarConfirm().show(p, year, month, day);
            }
        }, 11);

        builder.addButton(new GUIButton(Material.STRUCTURE_VOID, 1, Button.get("admin-day-clear-players")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminClearPlayersConfirm().show(p, year, month, day);
            }
        }, 12);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyMonth().show(p, year, month);
            }
        }, 13);

        builder.addButton(new GUIButton(Material.NAME_TAG, 1, Button.get("admin-day-set-custom-name", "name", dayObj.getName())) {
            @Override
            public void onLeftClick(Player p) {
                close(p);
                new AdminIptSetDayName(p, year, month, day);
            }
        }, 14);

        Material claimedIcon = dayObj.getClaimedIcon();
        if (claimedIcon == null) {
            claimedIcon = Material.BARRIER;
        }
        builder.addButton(new GUIButton(claimedIcon, 1, Button.get("admin-day-claimed-icon")) {
            @Override
            public void onItemDrag(Player p, ItemStack is) {
                if (is != null) {
                    dayObj.setClaimedIcon(is.clone().getType());
                    new AdminModifyDay().show(p, year, month, day);
                }
            }
        }, 15);

        Material lockedIcon = dayObj.getLockedIcon();
        boolean defaultIcon = false;
        if (lockedIcon == null) {
            lockedIcon = Advent.getYear(year).getMonth(month - 1).getDefaultLockedIcon();
            defaultIcon = true;
        }
        builder.addButton(new GUIButton(lockedIcon, 1, Button.get("admin-day-locked-icon", "default", Boolean.toString(defaultIcon))) {
            @Override
            public void onItemDrag(Player p, ItemStack is) {
                if (is != null) {
                    dayObj.setLockedIcon(is.clone().getType());
                    new AdminModifyDay().show(p, year, month, day);
                }
            }
        }, 16);

        if (dayObj.isHidden()) {
            builder.addButton(new GUIButton(Material.RED_STAINED_GLASS, 1, Button.get("admin-day-hidden", "hidden", "true")) {
                @Override
                public void onLeftClick(Player p) {
                    dayObj.setHidden(false);
                    new AdminModifyDay().show(p, year, month, day);
                }
            }, 17);
        } else {
            builder.addButton(new GUIButton(Material.GREEN_STAINED_GLASS, 1, Button.get("admin-day-hidden", "hidden", "false")) {
                @Override
                public void onLeftClick(Player p) {
                    dayObj.setHidden(true);
                    new AdminModifyDay().show(p, year, month, day);
                }
            }, 17);
        }

        return builder.build(p);
    }

}
