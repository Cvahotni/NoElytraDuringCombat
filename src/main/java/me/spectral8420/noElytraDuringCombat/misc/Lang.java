package me.spectral8420.noElytraDuringCombat.misc;

import me.spectral8420.noElytraDuringCombat.config.CustomConfig;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class Lang {
    private static final HashMap<String, String> messages = new HashMap<>();

    public static void getData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("lang");

        addMessage("prefix", langConfig);
        addMessage("glideCancel", langConfig);
        addMessage("reload", langConfig);
        addMessage("save", langConfig);
    }

    public static void addMessage(String key, CustomConfig langConfig) {
        try {
            messages.put(key, (String) langConfig.get(key));
        }

        catch (Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Failed to find message due to an exception: " + e);
        }
    }

    public static String getMessage(String key, boolean includePrefix) {
        if(!messages.containsKey(key)) return key;
        return includePrefix ? messages.get("prefix") + messages.get(key) : messages.get(key);
    }
}