package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Months;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AdminModifyYear implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-year-config", "year", Integer.toString(year)));
        builder.setSize(MenuSize.SIX_ROWS);
        builder.addButton(new GUIButton(), 45, 46, 48, 49, 50, 52, 53);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("previous-page")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyYear().show(p, year - 1);
            }
        }, 47);

        builder.addButton(new GUIButton(Material.YELLOW_STAINED_GLASS_PANE, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminMenu().show(p);
            }
        }, 49);

        builder.addButton(new GUIButton(Material.GREEN_STAINED_GLASS_PANE, 1, Button.get("next-page")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyYear().show(p, year + 1);
            }
        }, 51);

        for (Months m : Months.values()) {
            if (Advent.getYear(year) != null && Advent.getYear(year).getMonth(m.getNumber() - 1) != null) {
                builder.addButton(new GUIButton(Material.LIME_SHULKER_BOX, 1, Button.get("admin-configure-month", "month", m.getName())) {
                    @Override
                    public void onLeftClick(Player p) {
                        new AdminModifyMonth().show(p, year, m.getNumber());
                    }
                }, m.getSlot());
            } else {
                builder.addButton(new GUIButton(Material.GRAY_SHULKER_BOX, 1, Button.get("admin-create-month", "month", m.getName())) {
                    @Override
                    public void onLeftClick(Player p) {
                        new AdminCreateMonthConfirm().show(p, year, m.getNumber());
                    }
                }, m.getSlot());

            }
        }

        return builder.build(p);
    }

}
