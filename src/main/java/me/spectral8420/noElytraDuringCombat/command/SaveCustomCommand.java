package me.spectral8420.noElytraDuringCombat.command;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import me.spectral8420.noElytraDuringCombat.config.CustomConfigManager;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SaveCustomCommand extends CustomCommand {
    @Override
    public String getName() {
        return "nedcsave";
    }

    @Override
    public List<CustomCommandArgumentType> arguments() {
        return List.of();
    }

    @Override
    public void onCommand(NoElytraDuringCombat plugin, CommandSender sender, String[] args) {
        CustomConfigManager.reload(plugin);
        sender.sendMessage(Lang.getMessage("save", true));
    }
}
