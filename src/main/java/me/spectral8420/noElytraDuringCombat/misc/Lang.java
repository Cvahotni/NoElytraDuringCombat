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
        addMessage("fireworkCancel", langConfig);
        addMessage("glideTimerDone", langConfig);
        addMessage("fireworkTimerDone", langConfig);
        addMessage("reload", langConfig);
        addMessage("save", langConfig);
        addMessage("combatCooldownBossBar", langConfig);
        addMessage("fireworkCooldownBossBar", langConfig);
    }

    public static void addMessage(String key, CustomConfig langConfig) {
        try {
            if(!langConfig.has(key)) {
                ConsoleHelper.sendMessage(
                        ChatColor.RED +
                                "Unable to find language key in lang.yml for " + key +
                                ", to fix this, please update the lang.yml file, " +
                                "add the key and value to it, or delete the lang.yml file to refresh it."
                );

                messages.put(key, key);
            }

            else {
                messages.put(key, (String) langConfig.get(key));
            }
        }

        catch (Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Failed to find message due to an exception: " + e);
        }
    }

    public static String getMessage(String key, boolean includePrefix) {
        if(!messages.containsKey(key)) {
            return key;
        }

        String foundMessage = messages.get(key);

        if(foundMessage == null) {
            return key;
        }

        if(foundMessage.isEmpty()) {
            return key;
        }

        return includePrefix ? messages.get("prefix") + foundMessage : foundMessage;
    }
}