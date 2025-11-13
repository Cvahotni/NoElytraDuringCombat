package me.spectral8420.noElytraDuringCombat.command;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CustomCommand {
    public abstract String getName();
    public abstract List<CustomCommandArgumentType> arguments();
    public abstract void onCommand(NoElytraDuringCombat plugin, CommandSender sender, String[] args);
}