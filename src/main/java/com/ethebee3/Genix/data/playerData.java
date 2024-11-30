package com.ethebee3.Genix.data;

import com.ethebee3.Genix.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class playerData {

    private static Main plugin;
    private static Map<UUID, YamlConfiguration> playerDataMap;

    // Constructor to initialize the plugin and the map
    public static void playerData(Main plugin) {
        playerData.plugin = plugin;
        playerData.playerDataMap = new HashMap<>();
    }

    // Method to load player data
    public static YamlConfiguration getDataForPlayer(UUID playerUUID) {
        
        if (playerDataMap.containsKey(playerUUID)) {
            return playerDataMap.get(playerUUID);
        }

        File playerFile = new File(plugin.getDataFolder(), "playerdata/" + playerUUID.toString() + ".yml");
        File playerDataFolder = new File(plugin.getDataFolder(), "playerdata/");
        if (!playerDataFolder.exists()) playerDataFolder.mkdir();
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        YamlConfiguration playerYaml = YamlConfiguration.loadConfiguration(playerFile);
        playerDataMap.put(playerUUID, playerYaml);

        // Schedule a task to unload the player data after 200 ticks (10 seconds)
        new BukkitRunnable() {
            @Override
            public void run() {
                unloadDataForPlayer(playerUUID);
            }
        }.runTaskLater(plugin, 200L);

        return playerDataMap.get(playerUUID);
    }

    // Method to unload player data
    public static void unloadDataForPlayer(UUID playerUUID) {
        if (playerDataMap.containsKey(playerUUID)) {
            YamlConfiguration playerYaml = playerDataMap.get(playerUUID);
            savePlayerDataToFile(playerUUID, playerYaml);
            playerDataMap.remove(playerUUID);
        }
    }

    // Method to save player data back to the file
    private static void savePlayerDataToFile(UUID playerUUID, YamlConfiguration playerYaml) {
        File playerFile = new File(plugin.getDataFolder(), "playerdata/" + playerUUID.toString() + ".yml");
        try {
            playerYaml.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
