package me.spectral8420.noElytraDuringCombat.command;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CustomCommandKit implements CommandExecutor {
    private final NoElytraDuringCombat plugin;

    public CustomCommandKit(NoElytraDuringCombat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        CustomCommand customCommand = CustomCommandManager.getCommand(command.getName());

        if(customCommand == null) {
            return false;
        }

        customCommand.onCommand(plugin, commandSender, args);
        return false;
    }
}