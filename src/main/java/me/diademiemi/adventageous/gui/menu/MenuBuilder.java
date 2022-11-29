package me.diademiemi.adventageous.gui.menu;

import me.diademiemi.adventageous.gui.GUIButton;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuBuilder {
    private String title;
    private MenuSize size;
    public HashMap<Integer, GUIButton> buttons;

    public MenuBuilder(String title) {
        this.title = title;
        this.buttons = new HashMap<Integer, GUIButton>();
    }

    public MenuBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MenuBuilder setSize(MenuSize size) {
        this.size = size;
        return this;
    }

    public MenuBuilder addButton(GUIButton button, int... slot) {
        if (slot.length == 0) {
            buttons.put(buttons.size() + 1, button);
        } else {
            for (int i : slot) {
                buttons.put(i, button);
            }
        }
        return this;
    }

    public Menu build(Player player) {
        return new Menu(player, size, title, buttons);
    }

}
