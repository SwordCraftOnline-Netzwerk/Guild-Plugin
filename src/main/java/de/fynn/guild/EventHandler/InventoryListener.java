package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.lang.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class InventoryListener implements Listener {

    private static HashMap<Player,Inventory> inventorys = new HashMap<>();

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory()==null||event.getCurrentItem()==null){
            return;
        }
        Player player = ((Player)event.getClickedInventory().getHolder());
        if(inventorys.get(player) == event.getClickedInventory()){
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
            if(itemName.equals(Main.getTitle(player,"createGuild"))){
                player.sendMessage(Main.getMsg(player,"guildCreated.askGuildName"));
                PlayerChatListener.observedPlayerCreate.add(player);
                inventorys.remove(player);
                event.getView().close();
            }else if(itemName.equals(Main.getTitle(player,"leaveGuild"))){
                player.sendMessage(Main.getMsg(player,"leaveGuild.player"));
                for (UUID uuid:
                        Main.guildManager.getPlayerGuild(player).getMembers()) {
                    Bukkit.getPlayer(uuid).sendMessage(Main.getMsg(player,Bukkit.getPlayer(uuid),"leaveGuild.guild"));
                }
                Main.guildManager.removeMember(player.getUniqueId().toString(),
                        Main.guildManager.getPlayerGuild(player).getGuildName());
                inventorys.remove(player);
                event.getView().close();
            }else if(itemName.equals(Main.getTitle(player,"closeGuild"))){
                player.sendMessage(Main.getMsg(player,"guildClosed"));
                for (UUID uuid:
                        Main.guildManager.getPlayerGuild(player).getMembers()) {
                    Bukkit.getPlayer(uuid).sendMessage(Main.getMsg(Bukkit.getPlayer(uuid),"guildClosed"));
                }
                Main.guildManager.closeGuild(Main.guildManager.getPlayerGuild(player).getGuildName());
                inventorys.remove(player);
                event.getView().close();
            }else if(itemName.equals(Main.getTitle(player,"kickMembers"))){
                inventorys.remove(player);
                event.getView().close();
                Inventory inv = GUIBuilder.buildKickGUI(player);
                player.openInventory(inv);
                addInv(player,inv);
            }
            event.setCancelled(true);
        }
    }

    public static void addInv(Player holder, Inventory inv){
        inventorys.put(holder, inv);
    }

}
