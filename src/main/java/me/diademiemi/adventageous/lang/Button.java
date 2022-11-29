package me.diademiemi.adventageous.lang;

import java.util.ArrayList;

public class Button {

    public static String[] get(String key, String... replacements) {
        ArrayList<String> button = (ArrayList<String>) Lang.getButton(key).clone();
        // Replace variables
        for (int i = 0; i < button.size(); i++) {
            button.set(i, Format.format(button.get(i), replacements));
        }
        return button.toArray(new String[button.size()]);
    }

}
