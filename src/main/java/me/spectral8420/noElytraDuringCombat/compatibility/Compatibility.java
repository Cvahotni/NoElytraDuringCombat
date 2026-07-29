package me.spectral8420.noElytraDuringCombat.compatibility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Compatibility {
    public static boolean isCombatLogXEnabled() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        return pluginManager.isPluginEnabled("CombatLogX");
    }
}
