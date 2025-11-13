package me.spectral8420.noElytraDuringCombat.misc;

import me.spectral8420.noElytraDuringCombat.config.CustomConfig;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import org.bukkit.ChatColor;

public class Settings {
    private static int combatTimeInSeconds = 30;

    public static void getData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("settings");

        if(!langConfig.has("combatTimeInSeconds")) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Settings does not have the required variables, reverting!");
            return;
        }

        combatTimeInSeconds = (int) langConfig.get("combatTimeInSeconds");
    }

    public static void setData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("settings");
        langConfig.set("combatTimeInSeconds", combatTimeInSeconds);
    }

    public static int getCombatTimeInSeconds() {
        return combatTimeInSeconds;
    }
}
