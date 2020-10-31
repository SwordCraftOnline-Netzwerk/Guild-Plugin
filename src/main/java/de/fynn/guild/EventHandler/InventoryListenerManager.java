package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.guild.GuildMemberInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class InventoryListenerManager {

    public void handleEvent(Player player, Inventory inv, InventoryType inventoryType, InventoryClickEvent event){
        switch (inventoryType){
            case CREATE:
                onCreateGUI(player, inv, event);
                break;
            case MANAGE:
                onManageGUI(player, inv, event);
                break;
            case KICK:
                onKickGUI(player, inv, event);
                break;
            case LIST_MEMBERS:
                onListMembersGUI(player, inv, event);
                break;
            case INVITE:
                onInviteGUI(player, inv, event);
                break;
        }
    }

    private void onCreateGUI(Player player, Inventory inv, InventoryClickEvent event){
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.getTitle(player,"createGuild"))){
            player.sendMessage(Main.getMsg(player,"guildCreated.askGuildName"));
            PlayerChatListener.observedPlayerCreate.add(player);
            closeInv(event);
        }
    }

    private void onManageGUI(Player player, Inventory inv, InventoryClickEvent event){
        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
        //leave
        if(itemName.equals(Main.getTitle(player,"leaveGuild"))){
            player.sendMessage(Main.getMsg(player,"leaveGuild.player"));
            sendMsgToGuildMembers(player,"leaveGuild.guild");
            Main.guildManager.removeMember(player.getUniqueId().toString(),
                    Main.guildManager.getPlayerGuild(player).getGuildName());
            closeInv(event);
        }
        //close
        else if(itemName.equals(Main.getTitle(player,"closeGuild"))){
            player.sendMessage(Main.getMsg(player,"guildClosed"));
            sendMsgToGuildMembers(player,"guildClosed");
            Main.guildManager.closeGuild(Main.guildManager.getPlayerGuild(player).getGuildName());
            closeInv(event);
        }
        //kick
        else if(itemName.equals(Main.getTitle(player,"kickMembers"))){
            closeInv(event);
            GuildMemberInventory inventory = GUIBuilder.buildMembersGUI(player,"inventorys.kickMembers", false);
            player.openInventory(inventory.getInventory());
            InventoryListener.addInv(player,inventory.getInventory(),InventoryType.KICK);
            InventoryListener.addGuildMemberInventory(player,inventory);
        }
        //list
        else if(itemName.equals(Main.getTitle(player,"listMembers"))){
            closeInv(event);
            GuildMemberInventory inventory = GUIBuilder.buildMembersGUI(player,"inventorys.listMembers",true);
            player.openInventory(inventory.getInventory());
            InventoryListener.addInv(player,inventory.getInventory(),InventoryType.LIST_MEMBERS);
            InventoryListener.addGuildMemberInventory(player,inventory);
        }
    }

    private void onKickGUI(Player player, Inventory inv, InventoryClickEvent event){
        GuildMemberInventory guildInv = InventoryListener.getGuildMemberInv(player);
        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
        if(itemName.equals(Main.getTitle(player,"next"))){
            if(guildInv.hasNextPage()){
                closeInv(event);
                guildInv.nextPage();
                player.openInventory(guildInv.getInventory());
            }
        }else if(itemName.equals(Main.getTitle(player,"previous"))){
            if(guildInv.hasPreviousPage()){
                closeInv(event);
                guildInv.previousPage();
                player.openInventory(guildInv.getInventory());
            }
        }else if(itemName.equals(Main.getTitle(player,"back"))){
            closeInv(event);
            Inventory inventory = GUIBuilder.buildGuildGUI(player,Main.guildManager.isLeader(player));
            player.openInventory(inventory);
            InventoryListener.addInv(player,inventory,InventoryType.MANAGE);
        }else {
            Main.guildManager.removeMember(Bukkit.getPlayer(itemName).getUniqueId().toString(),Main.guildManager.getPlayerGuild(player).getGuildName());
            closeInv(event);
            return;
        }
        InventoryListener.addGuildMemberInventory(player,guildInv);
    }

    private void onInviteGUI(Player player, Inventory inv, InventoryClickEvent event){

    }

    private void onListMembersGUI(Player player, Inventory inv, InventoryClickEvent event){
        GuildMemberInventory guildInv = InventoryListener.getGuildMemberInv(player);
        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
        if(itemName.equals(Main.getTitle(player,"next"))){
            if(guildInv.hasNextPage()){
                closeInv(event);
                guildInv.nextPage();
                player.openInventory(guildInv.getInventory());
            }
        }else if(itemName.equals(Main.getTitle(player,"previous"))){
            if(guildInv.hasPreviousPage()){
                closeInv(event);
                guildInv.previousPage();
                player.openInventory(guildInv.getInventory());
            }
        }else if(itemName.equals(Main.getTitle(player,"back"))) {
            closeInv(event);
            Inventory inventory = GUIBuilder.buildGuildGUI(player, Main.guildManager.isLeader(player));
            player.openInventory(inventory);
            InventoryListener.addInv(player, inventory, InventoryType.MANAGE);
        }
    }

    private void closeInv(InventoryClickEvent event){
        event.getView().close();
        InventoryListener.removeInv((Player) event.getClickedInventory().getHolder());
    }

    private void sendMsgToGuildMembers(Player player, String msg){
        for (UUID uuid:
                Main.guildManager.getPlayerGuild(player).getMembers()) {
            Bukkit.getPlayer(uuid).sendMessage(Main.getMsg(Bukkit.getPlayer(uuid),msg));
        }
    }

}
