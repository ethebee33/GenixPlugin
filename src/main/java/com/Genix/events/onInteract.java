package com.Genix.events;

import com.Genix.utils.TimeUtils;
import com.Genix.utils.chatUtils;
import com.Genix.utils.worldguardUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Genix.Main;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Map;

public class onInteract implements Listener {
    private final Main plugin;
    public onInteract(Main serverPlugin) {
        this.plugin = serverPlugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        //remove comment in order to make it both air and block
        if (/*event.getAction() == Action.RIGHT_CLICK_AIR ||*/ event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem().getType() == Material.BLUE_STAINED_GLASS) handleBubble(event); event.setCancelled(true);
        }
    }

    private static Map<Player, Integer> bubbleCooldown;
    private static void handleBubble(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location bubbleLocation = event.getPlayer().getLocation();
        if (bubbleCooldown.containsKey(player)) { if (bubbleCooldown.get(player) < TimeUtils.getCurrentTime()) return; chatUtils.sendMessage(player, "you still need to wait "+(bubbleCooldown.get(player) - TimeUtils.getCurrentTime()), true);}
        if (!worldguardUtils.isPlayerInRegion(player, "")) return; //makes it so it doesnt activate if in pvp region
        player.setCooldown(player.getInventory().getItemInMainHand().getType(), TimeUtils.secondsToTicks(120));
        bubbleCooldown.put(player, TimeUtils.secondsFromNow(120));
    }

    private void createForceField(Player player, Location center) {

        player.playSound(player.getLocation(), "block.beacon.activate", 1, 1);


        int radius = 5;
        for (int y = 0; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius && x * x + y * y + z * z >= (radius - 1) * (radius - 1)) {
                        Location blockLocation = center.clone().add(new Vector(x, y, z));
                        Block block = blockLocation.getBlock();
                        if (block.getType() == Material.AIR && worldguardUtils.isLocationInRegion(blockLocation, "")) {
                            block.setType(Material.BLUE_STAINED_GLASS);
                        }
                    }
                }
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int y = 0; y <= radius; y++) {
                    for (int x = -radius; x <= radius; x++) {
                        for (int z = -radius; z <= radius; z++) {
                            if (x * x + y * y + z * z <= radius * radius && x * x + y * y + z * z >= (radius - 1) * (radius - 1)) {
                                Location blockLocation = center.clone().add(new Vector(x, y, z));
                                Block block = blockLocation.getBlock();
                                if (block.getType() == Material.BLUE_STAINED_GLASS) {
                                    block.setType(Material.AIR);
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskLater(plugin, TimeUtils.secondsToTicks(15));
    }


}
