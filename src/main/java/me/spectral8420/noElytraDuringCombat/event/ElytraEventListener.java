package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.CombatTimeTracker;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

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

        player.setGliding(false);
        player.sendMessage(Lang.getMessage("glideCancel", true));

        event.setCancelled(true);
    }
}
