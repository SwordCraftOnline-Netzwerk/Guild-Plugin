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

import java.util.HashMap;
import java.util.UUID;

public class InventoryListener implements Listener {

    private static HashMap<Player, Inventory> inventorys = new HashMap<>();
    private static HashMap<Player, GuildMemberInventory> guildMemberInventorys = new HashMap<>();

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory()==null||event.getCurrentItem()==null){
            return;
        }
        Player player = ((Player)event.getClickedInventory().getHolder());
        if(inventorys.get(player) == event.getClickedInventory()){
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
            if(event.getView().getTitle().equals(Main.getTitle(player,"inventorys.kickMembers"))){
                GuildMemberInventory guildInv = guildMemberInventorys.get(player);
                if(itemName.equals(Main.getTitle(player,"next"))){
                    if(guildInv.hasNextPage()){
                        guildInv.nextPage();
                    }
                }else if(itemName.equals(Main.getTitle(player,"previous"))){
                    if(guildInv.hasPreviousPage()){
                        guildInv.previousPage();
                    }
                }else if(itemName.equals(Main.getTitle(player,"back"))){
                    guildMemberInventorys.remove(player);
                    event.getView().close();
                    Inventory inv = GUIBuilder.buildGuildGUI(player,Main.guildManager.isLeader(player));
                    player.openInventory(inv);
                    addInv(player,inv);
                }else {
                    Main.guildManager.removeMember(Bukkit.getPlayer(itemName).getUniqueId().toString(),Main.guildManager.getPlayerGuild(player).getGuildName());
                    inventorys.remove(player);
                    event.getView().close();
                }
            }
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
                GuildMemberInventory inv = GUIBuilder.buildKickGUI(player);
                player.openInventory(inv.getInventory());
                addInv(player,inv.getInventory());
                guildMemberInventorys.put(player,inv);
            }
            event.setCancelled(true);
        }
    }

    public static void addInv(Player holder, Inventory inv){
        inventorys.put(holder, inv);
    }

}
