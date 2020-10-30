package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.guild.GuildMemberInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class InventoryListener implements Listener {

    private static HashMap<Player, List<Object>> inventorys = new HashMap<>();
    private static HashMap<Player, GuildMemberInventory> guildMemberInventorys = new HashMap<>();

    private InventoryListenerManager inventoryListenerManager = new InventoryListenerManager();

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory()==null||event.getCurrentItem()==null){
            return;
        }
        Player player = ((Player)event.getClickedInventory().getHolder());
        if(inventorys.containsKey(player)){
            inventoryListenerManager.handleEvent(player, (Inventory) inventorys.get(player).get(0), (InventoryType) inventorys.get(player).get(1), event);
        }
        event.setCancelled(true);
    }

    public static void addInv(Player holder, Inventory inv, InventoryType type){
        inventorys.put(holder, Arrays.asList(new Object[]{inv,type}.clone()));
    }

    public static void removeInv(Player holder){
        inventorys.remove(holder);
    }

    public static void addGuildMemberInventory(Player holder, GuildMemberInventory inventory){
        guildMemberInventorys.put(holder, inventory);
    }

    public static GuildMemberInventory getGuildMemberInv(Player player){
        GuildMemberInventory inv = guildMemberInventorys.get(player);
        guildMemberInventorys.remove(player);
        return inv;
    }

}
