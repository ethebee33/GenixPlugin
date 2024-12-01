package com.Genix.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

//temp data config import


public class ClearChatCMD implements CommandExecutor {

    private final JavaPlugin plugin;

    public ClearChatCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String command2, String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be used by players.");
            return true;
        }
        if (!commandSender.hasPermission("genix.cc")) {
            commandSender.sendMessage("You dont have the permission to execute this command");
            return true;
        }

        for(int i=0; i<300; i++ ) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage("");
            }
        }

        return true;
    }
}
