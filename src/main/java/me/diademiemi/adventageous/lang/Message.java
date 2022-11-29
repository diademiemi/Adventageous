package me.diademiemi.adventageous.lang;

import org.bukkit.command.CommandSender;

public class Message {

    public static void send(CommandSender p, String key, Boolean prefix, String... replacements) {
        if (prefix) {
            p.sendMessage(Format.format(Lang.getPrefix() + Lang.getMessage(key), replacements));
        } else {
            p.sendMessage(Format.format(Lang.getMessage(key), replacements));
        }

    }

    public static void send(CommandSender p, String key, String... replacements) {
        send(p, key, true, replacements);
    }

}
