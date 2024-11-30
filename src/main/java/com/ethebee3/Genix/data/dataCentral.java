package com.ethebee3.Genix.data;

import com.ethebee3.Genix.Main;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class dataCentral {

    private Main plugin;

    private YamlConfiguration yaml;



    public static void dataCentralLoad(Main plugin) {
        //playerData.playerData(plugin);
        //tempData.tempData(plugin);
        //wordsData.wordsData(plugin);
        //autocompData.autocompData(plugin);
    }

    public static boolean makeYml(File temp) {
        try {
            temp.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
