package me.spectral8420.noElytraDuringCombat.helper;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleHelper {
    private static final String prefix = "[NoElytraDuringCombat] ";

    public static void sendMessage(String message) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(prefix + message);
    }
}