package me.spectral8420.noElytraDuringCombat.combat;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import me.spectral8420.noElytraDuringCombat.helper.BossBarHelper;
import me.spectral8420.noElytraDuringCombat.helper.BossBarProgressType;
import me.spectral8420.noElytraDuringCombat.misc.Lang;
import me.spectral8420.noElytraDuringCombat.misc.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class TimeTracker {
    private static final HashMap<UUID, Integer> combatTimeLeft = new HashMap<>();
    private static final HashMap<UUID, Integer> fireworkTimeLeft = new HashMap<>();

    private static final HashMap<UUID, Double> combatMessageTimeLeft = new HashMap<>();
    private static final HashMap<UUID, Double> fireworkMessageTimeLeft = new HashMap<>();

    public static void startTask(NoElytraDuringCombat plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> modifyAllTimeLeft(-1), 0L, 20L);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> modifyAllMessageTimeLeft(-0.25), 0L, 5L);
    }

    public static void modifyAllTimeLeft(int amount) {
        List<UUID> combatUUIDsToRemove = new ArrayList<>();
        List<UUID> fireworkUUIDsToRemove = new ArrayList<>();

        for(UUID uuid : combatTimeLeft.keySet()) {
            int combatTimeLeftHere = combatTimeLeft.get(uuid);

            combatTimeLeft.replace(uuid, combatTimeLeftHere + amount);
            Player playerToNotify = Bukkit.getPlayer(uuid);

            if(playerToNotify == null) {
                continue;
            }

            if(combatTimeLeftHere <= 0) {
                combatUUIDsToRemove.add(uuid);
                playerToNotify.sendMessage(Lang.getMessage("glideTimerDone", true));
            }

            if(Settings.isDisplayCombatCooldownBossBar()) {
                BossBarHelper.setBossBar(BossBarProgressType.COMBAT_BAR, playerToNotify, combatTimeLeftHere, Settings.getCombatTimeInSeconds());
            }
        }

        for(UUID uuid : fireworkTimeLeft.keySet()) {
            int fireworkTimeLeftHere = fireworkTimeLeft.get(uuid);

            fireworkTimeLeft.replace(uuid, fireworkTimeLeftHere + amount);
            Player playerToNotify = Bukkit.getPlayer(uuid);

            if(playerToNotify == null) {
                continue;
            }

            if(fireworkTimeLeftHere <= 0) {
                fireworkUUIDsToRemove.add(uuid);
                playerToNotify.sendMessage(Lang.getMessage("fireworkTimerDone", true));
            }

            if(Settings.isDisplayFireworkCooldownBossBar()) {
                BossBarHelper.setBossBar(BossBarProgressType.FIREWORK_BAR, playerToNotify, fireworkTimeLeftHere, Settings.getFireWorkCooldownInSeconds());
            }
        }

        for(UUID uuid : combatUUIDsToRemove) {
            combatTimeLeft.remove(uuid);
        }

        for(UUID uuid : fireworkUUIDsToRemove) {
            fireworkTimeLeft.remove(uuid);
        }
    }

    public static void modifyAllMessageTimeLeft(double amount) {
        List<UUID> uuidsToRemoveMessage = new ArrayList<>();

        for(UUID uuid : combatMessageTimeLeft.keySet()) {
            combatMessageTimeLeft.replace(uuid, combatMessageTimeLeft.get(uuid) + amount);

            if(combatMessageTimeLeft.get(uuid) <= 0) {
                uuidsToRemoveMessage.add(uuid);
            }
        }

        for(UUID uuid : fireworkMessageTimeLeft.keySet()) {
            fireworkMessageTimeLeft.replace(uuid, fireworkMessageTimeLeft.get(uuid) + amount);

            if(fireworkMessageTimeLeft.get(uuid) <= 0) {
                uuidsToRemoveMessage.add(uuid);
            }
        }

        for(UUID uuid : uuidsToRemoveMessage) {
            combatMessageTimeLeft.remove(uuid);
            fireworkMessageTimeLeft.remove(uuid);
        }
    }

    public static void addCombatTimeLeft(UUID uuid, int amount) {
        combatTimeLeft.put(uuid, amount);
    }

    public static boolean containsCombatTimeLeft(UUID uuid) {
        return combatTimeLeft.containsKey(uuid);
    }

    public static void removeCombatTimeLeft(UUID uuid) {
        combatTimeLeft.remove(uuid);
    }

    public static void addCombatMessageTimeLeft(UUID uuid, double amount) {
        combatMessageTimeLeft.put(uuid, amount);
    }

    public static boolean containsCombatMessageTimeLeft(UUID uuid) {
        return combatMessageTimeLeft.containsKey(uuid);
    }

    public static void addFireworkTimeLeft(UUID uuid, int amount) {
        fireworkTimeLeft.put(uuid, amount);
    }

    public static boolean containsFireworkTimeLeft(UUID uuid) {
        return fireworkTimeLeft.containsKey(uuid);
    }

    public static void addFireworkMessageTimeLeft(UUID uuid, double amount) {
        fireworkMessageTimeLeft.put(uuid, amount);
    }

    public static boolean containsFireworkMessageTimeLeft(UUID uuid) {
        return fireworkMessageTimeLeft.containsKey(uuid);
    }

    public static double getCombatTimeLeft(UUID uuid) {
        if(!containsCombatTimeLeft(uuid)) {
            return 0.0;
        }

        return combatTimeLeft.get(uuid);
    }

    public static double getFireworkTimeLeft(UUID uuid) {
        if(!containsFireworkTimeLeft(uuid)) {
            return 0.0;
        }

        return fireworkTimeLeft.get(uuid);
    }
}
