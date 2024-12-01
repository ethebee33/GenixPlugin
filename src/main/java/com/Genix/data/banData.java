package com.Genix.data;

import com.Genix.utils.logUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static com.Genix.data.dataCentral.makeYml;

public class banData {
    static File banDataFile;
    public static YamlConfiguration banDataConfig = new YamlConfiguration();

    public static void banData(JavaPlugin plugin){
        banDataFile = new File(plugin.getDataFolder(), "banData.yml");
        if (!banDataFile.exists()) {
            if (makeYml(banDataFile)) banDataConfig = YamlConfiguration.loadConfiguration(banDataFile);
        } else {
            banDataConfig = YamlConfiguration.loadConfiguration(banDataFile);
        }
    }

    public static void saveBanData() {
        try {
            banDataConfig.save(banDataFile);
        } catch (IOException e) {
            logUtils.logError(e.getMessage());
        }
    }
}
