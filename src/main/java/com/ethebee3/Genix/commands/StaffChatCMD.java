package com.ethebee3.Genix.commands;

import com.ethebee3.Genix.utils.ChatUtils.StaffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class StaffChatCMD implements CommandExecutor {

    private final JavaPlugin plugin;

    public StaffChatCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String command2, String[] args) {
        //TODO: fix staffchat, not sure why its broken someone help :sob:
        if (!commandSender.hasPermission("genix.staffchat")) {
            commandSender.sendMessage("You must have staff to execute this command");
            return true;
        }

        if (args == null) {
            if (StaffUtils.hasSCon.contains(commandSender.getName())) {
                StaffUtils.hasSCon.remove(commandSender.getName());
            } else {
                StaffUtils.hasSCon.add(commandSender.getName());
            }
        } else {
            StaffUtils.sendStaffChatMessage(((Player) commandSender), Arrays.toString(args));
        }

        return true;
    }
}
