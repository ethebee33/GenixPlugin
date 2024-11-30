package com.ethebee3.Genix.saifpenguin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class skull implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            //get custom skull
            Player player = (Player) sender;
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), "sa1f");
            profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2MWRlZDhkODM4NWI5MTNhMDkxYWVmNDc4M2ZjY2JmZDNkMzhlZGQ5MGIyZTg5YjcyM2I1YTU3NDM0YmY0In19fQ=="))   ;
            Field profilefield;
            try{
                profilefield = skullmeta.getClass().getDeclaredField("profile");
                profilefield.setAccessible(true);
                profilefield.set(skullmeta, profile);

            }catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }

            skull.setItemMeta(skullmeta);
            player.getInventory().addItem(skull);




        }

        return true;

    }

}

