package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.TimeTracker;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ElytraEventListener implements Listener {
    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if(!(event.getEntity() instanceof Player player)) {
            return;
        }

        if(!TimeTracker.containsCombatTimeLeft(player.getUniqueId())) {
            return;
        }

        String bypassNode = "nedc.bypass";

        if(player.hasPermission(bypassNode)) {
            return;
        }

        double combatTimeLeft = TimeTracker.getCombatTimeLeft(player.getUniqueId());

        if(!TimeTracker.containsCombatMessageTimeLeft(player.getUniqueId())) {
            player.sendMessage(Lang.getMessage("glideCancel", true).replace("{time}", "" + (int) combatTimeLeft));
            TimeTracker.addCombatMessageTimeLeft(player.getUniqueId(), Settings.getNotifyInterval());
        }

        player.setGliding(false);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(!TimeTracker.containsCombatTimeLeft(player.getUniqueId())) {
            return;
        }

        String bypassNode = "nedc.bypass";

        if(player.hasPermission(bypassNode)) {
            return;
        }

        if(player.isGliding()) {
            if(!TimeTracker.containsCombatMessageTimeLeft(player.getUniqueId())) {
                player.sendMessage(Lang.getMessage("glideCancel", true));
                TimeTracker.addCombatMessageTimeLeft(player.getUniqueId(), Settings.getNotifyInterval());
            }

            player.setGliding(false);
        }
    }
}
