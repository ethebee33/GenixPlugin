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

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.ProtocolLibrary;


public class reportCMD implements CommandExecutor {
    private final JavaPlugin plugin;
    public reportCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String command2, String[] args) {
        Player commandsender2 = (Player) commandSender;
        if (args[1] == "gui") {
            switch (args[2]) {
                case "chat" -> {
                    closeBookForPlayer(commandsender2);
                    ItemStack bookgui = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) bookgui.getItemMeta();

                    bookMeta.setTitle("Report a player");
                    bookMeta.setAuthor("ethebee3");

                    String line1 = "&lReporting "+args[1]+" for breaking chat rules:";
                    String line2 = "What would you like to report them for:";

                    TextComponent line3 = new TextComponent("&4&lToxicity");
                    guiUtils.setClickEventToCommand(line3, "/report gui finish chat toxicity "+args[1]);

                    TextComponent line4 = new TextComponent("&4&lSlurs");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat toxicity "+args[1]);

                    TextComponent line5 = new TextComponent("&4&lDiscrimination");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat discrimination "+args[1]);

                    TextComponent line6 = new TextComponent("&4&lHate speech");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat hatespeech "+args[1]);

                    bookMeta.addPage(String.join("\n", line1, line2, line3.getText(), line4.getText(), line5.getText(), line6.getText()));
                    bookgui.setItemMeta(bookMeta);
                    openBookForPlayer(commandsender2, bookgui);
                }
                case "cheat" -> {
                    closeBookForPlayer(commandsender2);
                    ItemStack bookgui = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) bookgui.getItemMeta();

                    bookMeta.setTitle("Report a player");
                    bookMeta.setAuthor("ethebee3");

                    String line1 = "&lReporting " + args[1] + " for cheat:";
                    String line2 = "What would you like to report them for:";

                    TextComponent line3 = new TextComponent("&4&lKillaura");
                    guiUtils.setClickEventToCommand(line3, "/report gui finish chat toxicity " + args[1]);

                    TextComponent line4 = new TextComponent("&4&lAutoCrit");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat toxicity " + args[1]);

                    TextComponent line5 = new TextComponent("&4&lAutoTotem");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat discrimination " + args[1]);

                    TextComponent line6 = new TextComponent("&4&lOther");
                    guiUtils.setClickEventToCommand(line4, "/report gui finish chat hatespeech " + args[1]);

                    bookMeta.addPage(String.join("\n", line1, line2, line3.getText(), line4.getText(), line5.getText()));
                    bookgui.setItemMeta(bookMeta);
                    openBookForPlayer(commandsender2, bookgui);
                }
                case "finish" -> {
                    //TODO: finish report
                }
            }

        } else {
            ItemStack bookgui = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) bookgui.getItemMeta();

            bookMeta.setTitle("Report a player");
            bookMeta.setAuthor("ethebee3");

            String line1 = "&lReporting "+args[1];
            String line2 = "What would you like to report them for:\n";

            TextComponent line3 = new TextComponent("&4&lChat Reporting");
            guiUtils.setClickEventToCommand(line3, "/report gui cheat "+args[1]);

            TextComponent line4 = new TextComponent("&4&lCheat Reporting");
            guiUtils.setClickEventToCommand(line4, "/report gui chat "+args[1]);

            bookMeta.addPage(String.join("\n", line1, line2, line3.getText(), line4.getText()));
            bookgui.setItemMeta(bookMeta);
            openBookForPlayer(commandsender2, bookgui);
        }
        return true;
    }

    private void openBookForPlayer(Player player, ItemStack book) {
        try {
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.OPEN_BOOK);
            packet.getItemModifier().write(0, book);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeBookForPlayer(Player player) {
        try {
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.CLOSE_WINDOW);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void finishBookReport(String player, String type1, String type2, String extra) {

    }

}
