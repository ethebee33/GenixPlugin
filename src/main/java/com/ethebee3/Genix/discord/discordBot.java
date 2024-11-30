package com.ethebee3.Genix.discord;

import com.ethebee3.Genix.Main;
import org.bukkit.plugin.java.JavaPlugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import static com.ethebee3.Genix.utils.logUtils.logError;
import static com.ethebee3.Genix.utils.logUtils.logInfo;

public class discordBot {
    //nothing here yet, i have to disect discordsrv in order to do this
    //might just fork a discord bot plugin and add it here, its not public anyway so idrc
    //stupid test thing
    //this is another stupid test thing lmao


    private static DiscordApi api;

    public static void registerBot(Main main) {
        //temp disable because of errors
        //registerBot(main);
    }

    public void registerBot(JavaPlugin plugin) {
        new DiscordApiBuilder()
                .setToken("<Bot Token>")
                .login()
                .thenAccept(this::onConnectToDiscord)
                .exceptionally(error -> {
                    logError("Failed to connect to Discord! Disabling plugin!");
                    plugin.getPluginLoader().disablePlugin(plugin);
                    return null;
                });
    }

    public static void unregisterBot() {
        if (api != null) {
            // Make sure to disconnect the bot when the plugin gets disabled
            api.disconnect();
            api = null;
        }
    }

    private void onConnectToDiscord(DiscordApi api) {
        discordBot.api = api;

        // Log a message that the connection was successful and log the url that is needed to invite the bot
        logInfo("Connected to Discord as " + api.getYourself().getDiscriminatedName());
        logInfo("Open the following url to invite the bot: " + api.createBotInvite());
    }

    public void logMessage(int channelId) {

    }
}
