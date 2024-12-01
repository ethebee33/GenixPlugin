package com.Genix.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class playerUtils {

    public static Player getPlayerByName(String name) {
        return Bukkit.getPlayer(name);
    }

    public static Player getPlayerByUuid(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }
}
