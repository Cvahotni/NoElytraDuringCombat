package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.TimeTracker;
import me.spectral8420.noElytraDuringCombat.compatibility.CombatLogXCompatibility;
import me.spectral8420.noElytraDuringCombat.compatibility.Compatibility;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CombatEventListener implements Listener {
    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player victim)) {
            return;
        }

        if(!(event.getDamager() instanceof Player attacker)) {
            return;
        }

        if(TimeTracker.containsCombatTimeLeft(victim.getUniqueId())) {
            TimeTracker.removeCombatTimeLeft(victim.getUniqueId());
        }

        int combatTimeInSeconds = Settings.getCombatTimeInSeconds();

        if(Settings.isIntegrateWithCombatLogX() && Compatibility.isCombatLogXEnabled()) {
            combatTimeInSeconds = CombatLogXCompatibility.getAPI().getConfiguration().getDefaultTimer();
        }

        TimeTracker.addCombatTimeLeft(victim.getUniqueId(), combatTimeInSeconds);
        TimeTracker.addCombatTimeLeft(attacker.getUniqueId(), combatTimeInSeconds);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        if(TimeTracker.containsCombatTimeLeft(victim.getUniqueId())) {
            TimeTracker.removeCombatTimeLeft(victim.getUniqueId());
        }
    }
}
