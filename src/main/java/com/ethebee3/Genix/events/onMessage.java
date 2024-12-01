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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

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
