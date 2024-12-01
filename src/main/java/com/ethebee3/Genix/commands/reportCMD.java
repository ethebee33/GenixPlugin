package com.ethebee3.Genix.commands;

import com.ethebee3.Genix.utils.guiUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class reportCMD implements CommandExecutor {
    private final JavaPlugin plugin;
    public reportCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String command2, String[] args) {
        if (args[1] == "gui") {

        } else {
            ItemStack bookgui = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) bookgui.getItemMeta();

            bookMeta.setTitle("Report a player");
            bookMeta.setAuthor("ethebee3");

            String line1 = "&lReporting "+args[1];
            String line2 = "What would you like to report them for:";

            TextComponent line3 = new TextComponent("&4&lHacking");
            guiUtils.setClickEventToCommand(line3, "/report gui "+args[1]+":hacking");

            TextComponent line4 = new TextComponent("&4&lBreaking chat rules");
            guiUtils.setClickEventToCommand(line4, "");


        }
        return true;
    }
}
