package me.spectral8420.noElytraDuringCombat.misc;

import me.spectral8420.noElytraDuringCombat.config.CustomConfig;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import net.md_5.bungee.api.ChatColor;

public class Settings {
    private static int combatTimeInSeconds = 30;
    private static int fireWorkCooldownInSeconds = 15;
    private static double notifyInterval = 5.0;
    private static boolean useFireworkCooldown = false;
    private static boolean displayCombatCooldownBossBar = true;
    private static boolean displayFireworkCooldownBossBar = true;
    private static boolean integrateWithCombatLogX = true;

    public static void getData() {
        CustomConfig settingsConfig = CustomConfigManager.getConfig("settings");

        try {
            if(settingsConfig.has("combatTimeInSeconds")) {
                combatTimeInSeconds = (int) settingsConfig.get("combatTimeInSeconds");
            }

            if(settingsConfig.has("fireWorkCooldownInSeconds")) {
                fireWorkCooldownInSeconds = (int) settingsConfig.get("fireWorkCooldownInSeconds");
            }

            if(settingsConfig.has("notifyInterval")) {
                notifyInterval = (double) settingsConfig.get("notifyInterval");
            }

            if(settingsConfig.has("useFireworkCooldown")) {
                useFireworkCooldown = (boolean) settingsConfig.get("useFireworkCooldown");
            }

            if(settingsConfig.has("displayCombatCooldownBossBar")) {
                displayCombatCooldownBossBar = (boolean) settingsConfig.get("displayCombatCooldownBossBar");
            }

            if(settingsConfig.has("displayFireworkCooldownBossBar")) {
                displayFireworkCooldownBossBar = (boolean) settingsConfig.get("displayFireworkCooldownBossBar");
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
        CustomConfig settingsConfig = CustomConfigManager.getConfig("settings");

        try {
            settingsConfig.set("combatTimeInSeconds", combatTimeInSeconds);
            settingsConfig.set("fireWorkCooldownInSeconds", fireWorkCooldownInSeconds);
            settingsConfig.set("notifyInterval", notifyInterval);

            if(!settingsConfig.has("displayCombatCooldownBossBar")) {
                settingsConfig.set("displayCombatCooldownBossBar", displayCombatCooldownBossBar);
            }

            if(!settingsConfig.has("displayFireworkCooldownBossBar")) {
                settingsConfig.set("displayFireworkCooldownBossBar", displayFireworkCooldownBossBar);
            }

            if(!settingsConfig.has("useFireworkCooldown")) {
                settingsConfig.set("useFireworkCooldown", useFireworkCooldown);
            }

            if(!settingsConfig.has("integrateWithCombatLogX")) {
                settingsConfig.set("integrateWithCombatLogX", integrateWithCombatLogX);
            }
        }

        catch(Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Exception whilst saving settings: " + e);
        }
    }

    public static int getCombatTimeInSeconds() {
        return combatTimeInSeconds;
    }

    public static int getFireWorkCooldownInSeconds() {
        return fireWorkCooldownInSeconds;
    }

    public static double getNotifyInterval() {
        return notifyInterval;
    }

    public static boolean isUseFireworkCooldown() {
        return useFireworkCooldown;
    }

    public static boolean isDisplayCombatCooldownBossBar() {
        return displayCombatCooldownBossBar;
    }

    public static boolean isDisplayFireworkCooldownBossBar() {
        return displayFireworkCooldownBossBar;
    }

    public static boolean isIntegrateWithCombatLogX() {
        return integrateWithCombatLogX;
    }
}
