package com.ethebee3.Genix.events;

import com.ethebee3.Genix.Main;

public class eventLoader {
    public static void registerEvents(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(new onMessage(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new onJoin(plugin), plugin);
    }
}
