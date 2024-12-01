package com.Genix.events;

import com.Genix.Main;
import com.Genix.data.banData;
import com.Genix.utils.TimeUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class onJoin implements Listener {
    private final Main plugin;
    public onJoin(Main serverPlugin) {
        this.plugin = serverPlugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Object muted = banData.banDataConfig.get("banned."+player.getUniqueId());
        if (muted == "true") player.kickPlayer("");
        int tempmuted = (int) banData.banDataConfig.get("tempban."+player.getUniqueId());
        if (tempmuted > TimeUtils.getCurrentTime()) player.kickPlayer("");
    }
}
