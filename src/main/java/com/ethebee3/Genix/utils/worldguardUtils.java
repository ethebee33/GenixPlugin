package com.ethebee3.Genix.utils;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import org.bukkit.entity.Player;

public class worldguardUtils {

    public static boolean isPlayerInRegion(Player player, String regionName) {
        WorldGuard worldGuard = WorldGuard.getInstance();
        RegionManager regionManager = worldGuard.getPlatform().getRegionContainer()
                .get(BukkitAdapter.adapt(player.getWorld()));
        if (regionManager == null) {
            return false;
        }

        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(player.getLocation());
        BlockVector3 blockVector = loc.toVector().toBlockPoint();
        ApplicableRegionSet regions = regionManager.getApplicableRegions(blockVector);
        
        for (ProtectedRegion region : regions) {
            if (region.getId().equalsIgnoreCase(regionName)) {
                return true;
            }
        }

        return false;
    }

}
