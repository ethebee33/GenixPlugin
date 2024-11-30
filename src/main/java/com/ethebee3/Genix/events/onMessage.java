package com.ethebee3.Genix.events;

import com.ethebee3.Genix.utils.ChatUtils.color.Gradient;
import com.ethebee3.Genix.utils.TimeUtils;
import com.ethebee3.Genix.utils.chatUtils;
import com.ethebee3.Genix.utils.luckpermsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.ethebee3.Genix.Main;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.ethebee3.Genix.data.banData;

public class onMessage implements Listener {
    private final Main plugin;
    public onMessage(Main serverPlugin) {
        this.plugin = serverPlugin;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        //event.setCancelled(true);
        /*
        for(Player player : Bukkit.getOnlinePlayers()) {
            StringBuilder gradient = Gradient.formatGradientStringBuilder(event.getMessage(), "#000000", "#FFFFFF");
            //TODO: make a config part of playerData for gradient settings
            String message = chatUtils.formatMessageForPlayer(gradient, event.getPlayer());
            chatUtils.sendMessage(player, message, false);
        }

         */
        Player player = event.getPlayer();
        Object muted = banData.banDataConfig.get("muted."+player.getUniqueId());
        if (muted == "true") event.setCancelled(true);
        int tempmuted = (int) banData.banDataConfig.get("tempmute."+player.getUniqueId());
        if (tempmuted > TimeUtils.getCurrentTime()) event.setCancelled(true);

    }
    static String seperator = " ⇒ ";

    public static void customChat(AsyncPlayerChatEvent event) {
        String prefix = luckpermsUtils.getPrefix(event.getPlayer());
        String name = event.getPlayer().getName();
        String suffix = ""; //implement later
        String message = event.getMessage();

        event.setCancelled(true);
        for(Player player : Bukkit.getOnlinePlayers()) {
            String FormattedMessage = prefix + name + suffix + seperator + message;
            chatUtils.sendMessage(player, FormattedMessage, false);
        }
    }
}
