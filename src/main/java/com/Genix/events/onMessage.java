package com.Genix.events;

import com.Genix.utils.TimeUtils;
import com.Genix.utils.chatUtils;
import com.Genix.utils.luckpermsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Genix.Main;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.Genix.data.banData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class onMessage implements Listener {
    private final Main plugin;
    public onMessage(Main serverPlugin) {
        this.plugin = serverPlugin;
    }
    public static Map<Player, Consumer<AsyncPlayerChatEvent>> functionCallback = new HashMap<>();

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (functionCallback.containsKey(player)) {
            functionCallback.get(player).accept(event);
            functionCallback.remove(player);
        }

        Object muted = banData.banDataConfig.get("muted."+player.getUniqueId());
        if (muted == "true") event.setCancelled(true);
        Object tempmuted = banData.banDataConfig.get("tempmute."+player.getUniqueId());
        if (tempmuted != null) if ((int) tempmuted > TimeUtils.getCurrentTime()) event.setCancelled(true);
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
