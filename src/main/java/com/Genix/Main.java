package com.Genix;


import org.bukkit.plugin.java.JavaPlugin;

//changed to commands.spawnUtils.*;
//import ethebee3.genix.commands.pvpCMD;
//import commands.com.ethebee3.Genix.ClearChatCMD;
//import ethebee3.genix.commands.spawnUtils.SpawnCMD;
//import commands.com.ethebee3.Genix.StaffChatCMD;

import static com.Genix.data.dataCentral.dataCentralLoad;
import static com.Genix.events.eventLoader.registerEvents;
import static com.Genix.commands.commandLoader.registerCommands;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        dataCentralLoad(this);
        registerEvents(this);
        registerCommands(this);
    }

    @Override
    public void onDisable() {

    }

}
