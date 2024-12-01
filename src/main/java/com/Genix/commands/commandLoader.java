package com.Genix.commands;

import com.Genix.Main;

public class commandLoader {
    public static void registerCommands(Main plugin) {
        plugin.getCommand("tempban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("tempmute").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("ban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("mute").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("kick").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("unban").setExecutor(new punishmentCMD(plugin));
        plugin.getCommand("unmute").setExecutor(new punishmentCMD(plugin));

        plugin.getCommand("skull").setExecutor(new skullCMD(plugin));

        plugin.getCommand("report").setExecutor(new reportCMD(plugin));
    }
}
