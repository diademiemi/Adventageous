package me.diademiemi.adventageous.lang;

public class Title {
    public static String get(String key, String... replacements) {
        return Format.format(Lang.getTitle(key), replacements);
    }
}
