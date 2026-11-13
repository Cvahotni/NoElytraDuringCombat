package me.spectral8420.noElytraDuringCombat.helper;

import me.spectral8420.noElytraDuringCombat.misc.Lang;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BossBarHelper {
    private static final HashMap<UUID, BossBar> combatBossbars = new HashMap<>();
    private static final HashMap<UUID, BossBar> fireworkBossbars = new HashMap<>();

    public static void setBossBar(BossBarProgressType type, Player player, int time, int duration) {
        double calculatedTime = ((float) time) / ((float) duration);

        switch(type) {
            case COMBAT_BAR: {
                if(combatBossbars.containsKey(player.getUniqueId())) {
                    BossBar combatBossBar = combatBossbars.get(player.getUniqueId());
                    combatBossBar.removeAll();

                    combatBossbars.remove(player.getUniqueId());
                }

                if(time > 0) {
                    BossBar combatBossBar = Bukkit.createBossBar(
                            Lang.getMessage("combatCooldownBossBar", false).replace("{time}", "" + time),
                            BarColor.RED,
                            BarStyle.SOLID
                    );

                    combatBossBar.setProgress(calculatedTime);
                    combatBossBar.addPlayer(player);

                    combatBossbars.put(player.getUniqueId(), combatBossBar);
                }

                break;
            }

            case FIREWORK_BAR: {
                if(fireworkBossbars.containsKey(player.getUniqueId())) {
                    BossBar fireworkBossbar = fireworkBossbars.get(player.getUniqueId());
                    fireworkBossbar.removeAll();

                    fireworkBossbars.remove(player.getUniqueId());
                }

                if(time > 0) {
                    BossBar fireworkBossbar = Bukkit.createBossBar(
                            Lang.getMessage("fireworkCooldownBossBar", false).replace("{time}", "" + time),
                            BarColor.RED,
                            BarStyle.SOLID
                    );

                    fireworkBossbar.setProgress(calculatedTime);
                    fireworkBossbar.addPlayer(player);

                    fireworkBossbars.put(player.getUniqueId(), fireworkBossbar);
                }

                break;
            }
        }
    }

    public static void cleanUp() {
        for(BossBar bossBar : combatBossbars.values()) {
            bossBar.removeAll();
        }

        for(BossBar bossBar : fireworkBossbars.values()) {
            bossBar.removeAll();
        }
    }
}
