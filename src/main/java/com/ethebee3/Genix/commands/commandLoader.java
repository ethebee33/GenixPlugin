package com.ethebee3.Genix.commands;

import com.ethebee3.Genix.Main;

public class commandLoader {
    public static void registerCommands(Main plugin) {
        plugin.getCommand("tempban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("tempmute").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("ban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("mute").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("kick").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("unban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("unmute").setExecutor(new punishmentCMD(plugin));
    }
}
