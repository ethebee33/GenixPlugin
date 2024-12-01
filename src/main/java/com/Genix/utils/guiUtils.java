package com.Genix.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

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

    public static void setClickEventToCommand(TextComponent text, String command) {
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
    }
}
