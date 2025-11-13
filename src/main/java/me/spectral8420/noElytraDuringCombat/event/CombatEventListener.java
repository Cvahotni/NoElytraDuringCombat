package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.CombatTimeTracker;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatEventListener implements Listener {
    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player victim)) {
            return;
        }

        if(!(event.getDamager() instanceof Player attacker)) {
            return;
        }

        if(CombatTimeTracker.containsTimeLeft(victim.getUniqueId())) {
            CombatTimeTracker.removeTimeLeft(victim.getUniqueId());
        }

        CombatTimeTracker.addTimeLeft(victim.getUniqueId(), Settings.getCombatTimeInSeconds());
        CombatTimeTracker.addTimeLeft(attacker.getUniqueId(), Settings.getCombatTimeInSeconds());
    }
}
