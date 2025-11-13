package me.spectral8420.noElytraDuringCombat.config;

import me.spectral8420.noElytraDuringCombat.NoElytraDuringCombat;
import me.spectral8420.noElytraDuringCombat.helper.ConsoleHelper;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class CustomConfig {
    private File customConfigFile;
    private FileConfiguration customConfig;
    private final String name;
    private final boolean saveOnDisable;

    public CustomConfig(String name, boolean saveOnDisable) {
        this.name = name;
        this.saveOnDisable = saveOnDisable;
    }

    public boolean has(String path) {
        return customConfig.isSet(path);
    }

    public Object get(String path) {
        return customConfig.get(path);
    }

    public void set(String path, Object data) {
        customConfig.set(path, data);
    }

    public void save() {
        try {
            customConfig.save(customConfigFile);
        }

        catch (Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Exception whilst saving config " + name + ": " + e);
        }
    }

    public void load(NoElytraDuringCombat plugin) {
        try {
            createCustomConfig(plugin);
            customConfig.load(customConfigFile);
        }

        catch(IOException | InvalidConfigurationException e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Exception whilst loading config " + name + ": " + e);
        }
    }

    private void createCustomConfig(NoElytraDuringCombat plugin) {
        customConfigFile = new File(plugin.getDataFolder(), getConfigFileName());

        if(!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            copyFromPluginResources();
        }

        customConfig = new YamlConfiguration();
    }

    private void copyFromPluginResources() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getConfigFileName());

            if(inputStream == null) {
                ConsoleHelper.sendMessage(ChatColor.RED + "Failed to load config file from internal resources: " + getConfigFileName());
                return;
            }

            Files.copy(inputStream, customConfigFile.toPath());
        }

        catch(Exception e) {
            ConsoleHelper.sendMessage(ChatColor.RED + "Exception whilst copying config " + name + "from plugin resources: " + e);
        }
    }

    private String getConfigFileName() {
        return name + ".yml";
    }

    public boolean isSaveOnDisable() {
        return saveOnDisable;
    }
}