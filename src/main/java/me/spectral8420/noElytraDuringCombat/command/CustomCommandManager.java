package me.spectral8420.noElytraDuringCombat.command;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;
import java.util.List;

public class CustomCommandManager {
    private static final List<CustomCommand> commands = new ArrayList<>();

    public static void registerAllCommands(NoElytraDuringCombat plugin) {
        registerCommand(plugin, new ReloadCustomCommand());
        registerCommand(plugin, new SaveCustomCommand());
    }

    public static void registerCommand(NoElytraDuringCombat plugin, CustomCommand command) {
        commands.add(command);
        PluginCommand pluginCommand = plugin.getCommand(command.getName());

        if(pluginCommand == null) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Failed to register command: " + command.getName());
            return;
        }

        pluginCommand.setExecutor(new CustomCommandKit(plugin));
        pluginCommand.setTabCompleter(new CustomCommandTabCompleter());
    }

    public static CustomCommand getCommand(String name) {
        for(CustomCommand cmd : commands) {
            if(cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }

        return null;
    }
}