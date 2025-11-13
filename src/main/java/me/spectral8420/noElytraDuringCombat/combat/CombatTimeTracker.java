package me.spectral8420.noElytraDuringCombat.combat;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CombatTimeTracker {
    private static final HashMap<UUID, Integer> timeLeft = new HashMap<>();

    public static void startTask(NoElytraDuringCombat plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> modifyAllTimeLeft(-1), 0L, 20L);
    }

    public static void modifyAllTimeLeft(int amount) {
        List<UUID> uuidsToRemove = new ArrayList<>();

        for(UUID uuid : timeLeft.keySet()) {
            timeLeft.replace(uuid, timeLeft.get(uuid) + amount);

            if(timeLeft.get(uuid) <= 0) {
                uuidsToRemove.add(uuid);
            }
        }

        for(UUID uuid : uuidsToRemove) {
            timeLeft.remove(uuid);
        }
    }

    public static void addTimeLeft(UUID uuid, int amount) {
        timeLeft.put(uuid, amount);
    }

    public static boolean containsTimeLeft(UUID uuid) {
        return timeLeft.containsKey(uuid);
    }

    public static void removeTimeLeft(UUID uuid) {
        timeLeft.remove(uuid);
    }
}
