package me.spectral8420.noElytraDuringCombat;

import me.spectral8420.noElytraDuringCombat.combat.CombatTimeTracker;
import me.spectral8420.noElytraDuringCombat.command.CustomCommandManager;
import me.spectral8420.noElytraDuringCombat.compatibility.Compatibility;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.event.EventManager;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoElytraDuringCombat extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomCommandManager.registerAllCommands(this);
        EventManager.registerEvents(this);

        CustomConfigManager.register();
        CustomConfigManager.load(this);

        Lang.getData();
        Settings.getData();

        CombatTimeTracker.startTask(this);
        ConsoleHelper.sendMessage(ChatColor.GREEN + "NoElytraDuringCombat has been enabled!");

        Bukkit.getScheduler().runTask(this, () -> {
            if(Compatibility.isCombatLogXEnabled()) {
                ConsoleHelper.sendMessage(ChatColor.GREEN + "Will attempt to use default timings from CombatLogX if needed since it was found and running.");
            }
        });
    }

    @Override
    public void onDisable() {
        Settings.setData();
        CustomConfigManager.save();

        ConsoleHelper.sendMessage(ChatColor.RED + "NoElytraDuringCombat has been disabled!");
    }
}
