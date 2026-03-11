package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.CombatTimeTracker;
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

        if(!CombatTimeTracker.containsTimeLeft(player.getUniqueId())) {
            return;
        }

        String bypassNode = "nedc.bypass";

        if(player.hasPermission(bypassNode)) {
            return;
        }

        if(!CombatTimeTracker.containsMessageTimeLeft(player.getUniqueId())) {
            player.sendMessage(Lang.getMessage("glideCancel", true));
            CombatTimeTracker.addMessageTimeLeft(player.getUniqueId(), Settings.getNotifyInterval());
        }

        player.setGliding(false);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(!CombatTimeTracker.containsTimeLeft(player.getUniqueId())) {
            return;
        }

        String bypassNode = "nedc.bypass";

        if(player.hasPermission(bypassNode)) {
            return;
        }

        if(player.isGliding()) {
            if(!CombatTimeTracker.containsMessageTimeLeft(player.getUniqueId())) {
                player.sendMessage(Lang.getMessage("glideCancel", true));
                CombatTimeTracker.addMessageTimeLeft(player.getUniqueId(), Settings.getNotifyInterval());
            }

            player.setGliding(false);
        }
    }
}
