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

public class AdminModifyDay implements Dialog {


    @Override
    public Menu create(Player p, Object... args) {
        int year = (int) args[0];
        int month = (int) args[1];
        int day = (int) args[2];

        MenuBuilder builder = new MenuBuilder(Title.get("admin-day-config", "month", Integer.toString(month), "day", Integer.toString(day)));
        builder.setSize(MenuSize.TWO_ROWS);
        builder.addButton(new GUIButton(), 9, 10, 11, 12, 14, 15, 16, 17);

        builder.addButton(new GUIButton(Material.RED_STAINED_GLASS_PANE, 1, Button.get("return-previous")) {
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyMonth().show(p, year, month);
            }
        }, 13);


        return builder.build(p);
    }

}
