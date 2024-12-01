package com.Genix.utils.ChatUtils;

import com.Genix.utils.chatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class StaffUtils {
    public static List<String> hasSCon;

    public static void checkStaffChat(AsyncPlayerChatEvent event) {
        if (hasSCon.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            String staffMessage = "&6[&eStaff Chat&6] &a"+ event.getPlayer().getName() +": "+ event.getMessage();
            for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                chatUtils.sendMessage(onlinePlayer, staffMessage, false);
            }
        }
    }

    public static void sendStaffChatMessage(Player player, String message) {
        String staffMessage = "&6[&eStaff Chat&6] &a"+ player.getName() +": "+ message;
        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            chatUtils.sendMessage(onlinePlayer, staffMessage, false);
        }
    }
}
