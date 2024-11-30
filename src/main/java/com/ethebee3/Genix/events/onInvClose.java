package com.ethebee3.Genix.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.ethebee3.Genix.Main;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class onInvClose implements Listener {

    private final Main plugin;

    private static final Map<Player, Consumer<Inventory>> openInventories = new HashMap<>();

    public onInvClose(Main plugin) {
        this.plugin = plugin;
    }

    public static void registerInventoryCallback(Player inventoryName, Consumer<Inventory> callback) {
        openInventories.put(inventoryName, callback);
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory closedInventory = event.getInventory();
        if (openInventories.containsKey(player)) {
            Consumer<Inventory> callback = openInventories.get(player);
            callback.accept(closedInventory);
            openInventories.remove(player);
        }
    }
    /*
    example usage:
    onInvClose.registerInventoryCallback(player, inventory -> {
        System.out.println(player.getName() + " closed their inventory");
    });
     */
}
