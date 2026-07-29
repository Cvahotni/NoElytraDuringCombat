package me.spectral8420.noElytraDuringCombat.misc;

import me.spectral8420.noElytraDuringCombat.config.CustomConfig;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import net.md_5.bungee.api.ChatColor;

public class Settings {
    private static int combatTimeInSeconds = 30;
    private static double notifyInterval = 5.0;
    private static boolean integrateWithCombatLogX = true;

    public static void getData() {
        CustomConfig settingsConfig = CustomConfigManager.getConfig("settings");

        try {
            if(settingsConfig.has("combatTimeInSeconds")) {
                combatTimeInSeconds = (int) settingsConfig.get("combatTimeInSeconds");
            }

            if(settingsConfig.has("notifyInterval")) {
                notifyInterval = (double) settingsConfig.get("notifyInterval");
            }

            if(settingsConfig.has("integrateWithCombatLogX")) {
                integrateWithCombatLogX = (boolean) settingsConfig.get("integrateWithCombatLogX");
            }
        }

        catch(Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Exception whilst loading settings: " + e);
        }
    }

    public static void setData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("settings");

        langConfig.set("combatTimeInSeconds", combatTimeInSeconds);
        langConfig.set("notifyInterval", notifyInterval);
    }

    public static int getCombatTimeInSeconds() {
        return combatTimeInSeconds;
    }

    public static double getNotifyInterval() {
        return notifyInterval;
    }

    public static boolean isIntegrateWithCombatLogX() {
        return integrateWithCombatLogX;
    }
}
