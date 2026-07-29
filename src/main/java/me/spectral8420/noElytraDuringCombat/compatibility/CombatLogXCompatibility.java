package me.spectral8420.noElytraDuringCombat.compatibility;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class CombatLogXCompatibility {
    public static ICombatLogX getAPI() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Plugin plugin = pluginManager.getPlugin("CombatLogX");

        return (ICombatLogX) plugin;
    }
}
