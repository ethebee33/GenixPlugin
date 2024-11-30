package com.ethebee3.Genix.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import com.ethebee3.Genix.Main;
import java.io.File;

//this file will be used to open a gui of any sort with a name and set number of slots, similar to olly's skript
public class guiUtils {

    //public File guiYML = Main.guiFile;
    //public FileConfiguration guiConfig = Main.guiConfig;

    /*
    public static Inventory createGUI(Player Owner, String name, Integer slots) {
        Inventory inv = Bukkit.createInventory(Owner, slots, name);
        //not even sure why this exists
        //addToYML(inv);
        return inv;

    }

    public void addToYML(Inventory toSave) {
        String Title = toSave.toString();
        guiConfig.set(Title, toSave);
    }
    */
}
