package com.Genix.commands;

import com.Genix.events.onMessage;
import com.Genix.utils.guiUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.ProtocolLibrary;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class reportCMD implements CommandExecutor {
    private final JavaPlugin plugin;

    public reportCMD(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Usage: /report <gui/chat/cheat>");
            return true;
        }

        String subCommand = args[0].toLowerCase();
        switch (subCommand) {
            case "gui" -> handleGuiCommand(player, args);
            default -> player.sendMessage("Invalid command. Use /report gui.");
        }

        return true;
    }

    private void handleGuiCommand(Player player, String[] args) {
        if (args[0] == "gui") {
            switch (args[1]) {
                case "chat" -> {
                    String targetPlayer = args[2];
                    openReportBook(player, "Reporting " + targetPlayer + " for chat rules", List.of(
                            createRegularLine("Reporting "+targetPlayer+":"),
                            createClickableLine("§4§lToxicity", "/report gui finish chat toxicity " + targetPlayer),
                            createClickableLine("§4§lSlurs", "/report gui finish chat slurs " + targetPlayer),
                            createClickableLine("§4§lDiscrimination", "/report gui finish chat discrimination " + targetPlayer),
                            createClickableLine("§4§lHate Speech", "/report gui finish chat hatespeech " + targetPlayer)
                    ));
                }
                case "cheat" -> {
                    String targetPlayer = args[2];
                    openReportBook(player, "Reporting " + targetPlayer + " for cheating", List.of(
                            createRegularLine("Reporting "+targetPlayer+":"),
                            createClickableLine("§4§lKillaura", "/report gui finish cheat killaura " + targetPlayer),
                            createClickableLine("§4§lAutoCrit", "/report gui finish cheat autocrit " + targetPlayer),
                            createClickableLine("§4§lAutoTotem", "/report gui finish cheat autototem " + targetPlayer)//,
                        //createClickableLine("§4§lOther", "/report gui finish cheat other " + targetPlayer)
                    ));
                }
                case "finish" -> {
                    finishBookReport(player.getName(), args[4], args[2], args[3]);
                }
            }
        } else {
            String targetPlayer = args[0];
            openReportBook(player, "Report a player", List.of(
                    createRegularLine("Reporting "+targetPlayer+":"),
                    createClickableLine("§4§lKillaura", "/report gui finish cheat killaura " + targetPlayer),
                    createClickableLine("§4§lAutoCrit", "/report gui finish cheat autocrit " + targetPlayer),
                    createClickableLine("§4§lAutoTotem", "/report gui finish cheat autototem " + targetPlayer)//,
                    //createClickableLine("§4§lOther", "/report gui finish cheat other " + targetPlayer)
            ));
        }
    }

    private void openReportBook(Player player, String title, List<String> pages) {
        closeBookForPlayer(player);

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setTitle("Report a Player");
        meta.setAuthor("ethebee3");
        for (String page : pages) {
            meta.spigot().addPage(stringToBaseComponent(page));
        }

        book.setItemMeta(meta);
        player.openBook(book);
    }

    private String createClickableLine(String message, String command) {
        return "{\"text\": \"" + message + "\", \"clickEvent\": {\"action\": \"run_command\", \"value\": \"" + command + "\"}}";
    }

    private String createRegularLine(String message) {
        return "{\"text\": \"" + message + "\"}";
    }

    private void closeBookForPlayer(Player player) {
        try {
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.CLOSE_WINDOW);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finishBookReport(String reporter, String reported, String category, String type/*, String notes*/) {
        String webhookURL = "";

        String jsonPayload = String.format("""
                {
                    "content": "New player report:",
                    "embeds": [
                        {
                            "title": "Report from %s",
                            "color": 7506394,
                            "fields": [
                                {
                                    "name": "Player",
                                    "value": "%s is being reported for %s (%s)"
                                }
                            ],
                        }
                    ]
                }
                """, reporter, reported, category, type);

        sendWebhookRequest(webhookURL, jsonPayload);
    }

    public static void sendWebhookRequest(String webhookURL, String jsonPayload) {
        try {
            URL url = new URL(webhookURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Webhook sent successfully.");
            } else {
                System.out.println("Error sending webhook. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BaseComponent[] stringToBaseComponent(String message) {
        // Create a new TextComponent from the string
        TextComponent textComponent = new TextComponent(message);

        // If you need an array of BaseComponent, wrap it in an array
        return new BaseComponent[]{textComponent};
    }
}
