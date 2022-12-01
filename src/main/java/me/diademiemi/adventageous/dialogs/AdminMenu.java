package me.diademiemi.adventageous.dialogs;

import me.diademiemi.adventageous.AdventIO;
import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.gui.Dialog;
import me.diademiemi.adventageous.gui.GUIButton;
import me.diademiemi.adventageous.gui.menu.Menu;
import me.diademiemi.adventageous.gui.menu.MenuBuilder;
import me.diademiemi.adventageous.gui.menu.MenuSize;
import me.diademiemi.adventageous.lang.Button;
import me.diademiemi.adventageous.lang.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.time.LocalDate;

public class AdminMenu implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {
        MenuBuilder builder = new MenuBuilder(Title.get("admin-main-menu"));
        builder.setSize(MenuSize.ONE_ROW);
        builder.addButton(new GUIButton(Material.CLOCK, 1, Button.get("admin-configure-offset", "offset", Advent.getOffset())) {
            @Override
            public void onLeftClick(Player p) {
                close(p);
                new AdminIptSetOffset(p);
            }
        }, 2);

        builder.addButton(new GUIButton(Material.LIME_SHULKER_BOX, 1, Button.get("admin-configure-advents")) {
            LocalDate date = LocalDate.now();
            @Override
            public void onLeftClick(Player p) {
                new AdminModifyYear().show(p, date.getYear());
            }
        }, 4);

        builder.addButton(new GUIButton(Material.WRITABLE_BOOK, 1, Button.get("admin-write-data")) {
            @Override
            public void onLeftClick(Player p) {
                AdventIO.writeConfig();
            }
        }, 6);

        return builder.build(p);
    }

}
