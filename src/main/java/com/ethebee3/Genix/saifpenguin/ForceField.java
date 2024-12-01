package com.ethebee3.Genix.saifpenguin;

import com.ethebee3.Genix.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

import java.util.HashMap;

public class ForceField implements Listener {
    public HashMap<Player, Integer> playerCooldown = new HashMap<>();
    Main plugin;
    public ForceField(Main plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        //cancel if right click and places
        int currentTime = (int) System.currentTimeMillis();

        if (!playerCooldown.containsKey(player)) {
            if (player.getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lFORCE FIELD"))) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        event.setCancelled(true);
                        createForceField(player, player.getLocation());
                        playerCooldown.put(event.getPlayer(), currentTime + 120000);
                        player.setCooldown(player.getInventory().getItemInMainHand().getType(), 2400);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            playerCooldown.remove(player);
                        }, 2400);

                    }
                }
            }

        }else{
            getCooldown(player, event);
        }




    }

    public void getCooldown(Player player, PlayerInteractEvent event) {
        if (event.getHand().equals(EquipmentSlot.HAND) && event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(player.getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lFORCE FIELD"))) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        int remainingTime = (playerCooldown.get(player) - (int) System.currentTimeMillis()) / 1000;
                        player.sendMessage("Cooldown: " + remainingTime + " seconds");
                    }
                }
            }
        }
    }





    private void createForceField(Player player, Location center) {

        player.playSound(player.getLocation(), "block.beacon.activate", 1, 1);


        int radius = 5; // Radius of the hemisphere
        for (int y = 0; y <= radius; y++) {
            //iterate through the x, y, and z coordinates of the blocks in the hemisphere
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius && x * x + y * y + z * z >= (radius - 1) * (radius - 1)) {
                        //checks if the block is within the hemisphere and outside the inner hemisphere. The inner hemisphere
                        //is checked because we want the force field to be hollow. By checking if the block is outside the inner
                        //hemisphere, we know that the block is on the surface of the real hemisphere.
                        Location blockLocation = center.clone().add(new Vector(x, y, z)); //get the location of the block and add the x, y, and z coordinates to the clone of the center location
                        Block block = blockLocation.getBlock();
                        if (block.getType() == Material.AIR) {
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
        }.runTaskLater(plugin, 300);
    }
}