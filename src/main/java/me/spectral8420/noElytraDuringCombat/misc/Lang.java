package me.spectral8420.noElytraDuringCombat.misc;

import me.spectral8420.noElytraDuringCombat.config.CustomConfig;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;

import java.util.HashMap;

public class Lang {
    private static final HashMap<String, String> messages = new HashMap<>();

    public static void getData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("lang");

        addMessage("prefix", (String) langConfig.get("prefix"));
        addMessage("glideCancel", (String) langConfig.get("glideCancel"));
        addMessage("reload", (String) langConfig.get("reload"));
        addMessage("save", (String) langConfig.get("save"));
    }

    public static void addMessage(String key, String message) {
        messages.put(key, message);
    }

    public static String getMessage(String key, boolean includePrefix) {
        if(!messages.containsKey(key)) return key;
        return includePrefix ? messages.get("prefix") + messages.get(key) : messages.get(key);
    }
}