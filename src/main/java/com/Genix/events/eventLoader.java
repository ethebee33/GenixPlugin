package com.Genix.events;

import com.Genix.Main;

public class eventLoader {
    public static void registerEvents(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(new onMessage(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new onJoin(plugin), plugin);
    }
}
