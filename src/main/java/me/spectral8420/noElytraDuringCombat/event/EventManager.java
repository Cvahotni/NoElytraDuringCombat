package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import org.bukkit.Bukkit;

public class EventManager {
    public static void registerEvents(NoElytraDuringCombat plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new CombatEventListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ElytraEventListener(), plugin);
    }
}
