package me.diademiemi.adventageous.lang;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;

public class Format {

    public static String format(String msg, String... replacements) {
        return ChatColor.translateAlternateColorCodes('&', replace(msg, replacements));
    }

    public static String replace(String msg, String... replacements) {
        // Create dict for replacements
        // Loop through replacements
        // alternate between key and value

        HashMap<String, String> dict = new HashMap<>();
        for (int i = 0; i < replacements.length; i++) {
            if (i % 2 == 0) {
                dict.put(replacements[i], replacements[i + 1]);
            }
        }

        // Replace in string
        for (String key : dict.keySet()) {
            msg = msg.replace("{{ " + key + " }}", dict.get(key));
        }

        return msg;
    }


}
