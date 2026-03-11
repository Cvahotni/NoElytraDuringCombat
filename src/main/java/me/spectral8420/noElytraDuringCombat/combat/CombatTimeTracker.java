package me.spectral8420.noElytraDuringCombat.combat;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CombatTimeTracker {
    private static final HashMap<UUID, Integer> timeLeft = new HashMap<>();
    private static final HashMap<UUID, Double> messageTimeLeft = new HashMap<>();

    public static void startTask(NoElytraDuringCombat plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> modifyAllTimeLeft(-1), 0L, 20L);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> modifyAllMessageTimeLeft(-0.25), 0L, 5L);
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

    public static void modifyAllMessageTimeLeft(double amount) {
        List<UUID> uuidsToRemoveMessage = new ArrayList<>();

        for(UUID uuid : messageTimeLeft.keySet()) {
            messageTimeLeft.replace(uuid, messageTimeLeft.get(uuid) + amount);

            if(messageTimeLeft.get(uuid) <= 0) {
                uuidsToRemoveMessage.add(uuid);
            }
        }

        for(UUID uuid : uuidsToRemoveMessage) {
            messageTimeLeft.remove(uuid);
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

    public static void addMessageTimeLeft(UUID uuid, double amount) {
        messageTimeLeft.put(uuid, amount);
    }

    public static boolean containsMessageTimeLeft(UUID uuid) {
        return messageTimeLeft.containsKey(uuid);
    }
}
