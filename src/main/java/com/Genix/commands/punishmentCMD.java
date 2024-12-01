package com.Genix.commands;

import com.Genix.utils.TimeUtils;
import com.Genix.utils.luckpermsUtils;
import com.Genix.utils.playerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.Genix.data.banData;

public class punishmentCMD implements CommandExecutor {

    private final JavaPlugin plugin;

    public punishmentCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandName, String[] args) {
        Player commandSender2 = (Player) commandSender;
        switch (commandName) {
            case "tempban":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                tempban(args[2], playerUtils.getPlayerByName(args[1]));
            case "tempmute":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                tempmute(args[2], playerUtils.getPlayerByName(args[1]));
            case "ban":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                ban(playerUtils.getPlayerByName(args[1]));
            case "mute":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                mute(playerUtils.getPlayerByName(args[1]));
            case "kick":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                kick(playerUtils.getPlayerByName(args[1]));
            case "unban":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                unban(playerUtils.getPlayerByName(args[1]));
            case "unmute":
                if (!luckpermsUtils.hasPermissionForCommand(commandName, commandSender2)) return false;
                unmute(playerUtils.getPlayerByName(args[1]));
        }


        return true;
    }

    static String prefix = "&a&lGenix &7|";

    public static void tempban(String args, Player player) {
        int time = ((int) (System.currentTimeMillis() / 1000)) + TimeUtils.parseCompoundTime(args);
        banData.banDataConfig.set("tempban."+player.getUniqueId(), time);
        player.kickPlayer(prefix+"You have been tempbanned");
    }

    public static void tempmute(String args, Player player) {
        int time = ((int) (System.currentTimeMillis() / 1000)) + TimeUtils.parseCompoundTime(args);
        banData.banDataConfig.set("tempmute."+player.getUniqueId(), time);
    }

    public static void ban(Player player) {
        banData.banDataConfig.set("banned."+player.getUniqueId(), "true");
        player.kickPlayer(prefix+"You have been banned");
    }

    public static void mute(Player player) {
        banData.banDataConfig.set("muted."+player.getUniqueId(), "true");
    }

    public static void unban(Player player) {
        banData.banDataConfig.set("banned."+player.getUniqueId(), null);
        banData.banDataConfig.set("tempban."+player.getUniqueId(), null);
    }
    public static void unmute(Player player) {
        banData.banDataConfig.set("muted."+player.getUniqueId(), null);
        banData.banDataConfig.set("tempmute."+player.getUniqueId(), null);
    }

    public static void kick(Player player) {
        player.kickPlayer(prefix+" You have been kicked");
    }
}
