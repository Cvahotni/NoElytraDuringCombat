package me.spectral8420.noElytraDuringCombat.event;

import me.spectral8420.noElytraDuringCombat.combat.TimeTracker;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FireworkEventListener implements Listener {
    @EventHandler
    public void onPlayerFirework(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!Settings.isUseFireworkCooldown()) {
            return;
        }

        if(!player.isGliding()) {
            return;
        }

        ItemStack stack = event.getItem();

        if(stack == null) {
            return;
        }

        if(stack.getType() != Material.FIREWORK_ROCKET) {
            return;
        }

        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if(!TimeTracker.containsFireworkTimeLeft(player.getUniqueId())) {
            int fireworkTimeInSeconds = Settings.getFireWorkCooldownInSeconds();
            TimeTracker.addFireworkTimeLeft(player.getUniqueId(), fireworkTimeInSeconds);

            return;
        }

        double fireworkTimeLeft = TimeTracker.getFireworkTimeLeft(player.getUniqueId());

        if(!TimeTracker.containsFireworkMessageTimeLeft(player.getUniqueId())) {
            player.sendMessage(Lang.getMessage("fireworkCancel", true).replace("{time}", "" + (int) fireworkTimeLeft));
            TimeTracker.addFireworkMessageTimeLeft(player.getUniqueId(), Settings.getNotifyInterval());
        }

        event.setCancelled(true);
    }
}
