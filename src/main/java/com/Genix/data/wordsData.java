package com.Genix.data;

import com.Genix.utils.logUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static com.Genix.data.dataCentral.makeYml;

public class wordsData {
    static File wordsDataFile;
    public static YamlConfiguration wordsDataConfig = new YamlConfiguration();

    public static void wordsData(JavaPlugin plugin){
        wordsDataFile = new File(plugin.getDataFolder(), "words.yml");
        if (!wordsDataFile.exists()) {
            if (makeYml(wordsDataFile)) wordsDataConfig = YamlConfiguration.loadConfiguration(wordsDataFile);
        } else {
            wordsDataConfig = YamlConfiguration.loadConfiguration(wordsDataFile);
        }
    }

    public static void savewordsData() {
        try {
            wordsDataConfig.save(wordsDataFile);
        } catch (IOException e) {
            logUtils.logError(e.getMessage());
        }
    }
}
