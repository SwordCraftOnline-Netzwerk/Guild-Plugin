package de.fynn.guild.EventHandler;

import de.fynn.guild.GUIBuilder;
import de.fynn.guild.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory()==null||event.getCurrentItem()==null){
            return;
        }
        if(event.getView().getTitle().equals("%GuildInv%")){
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("%CreateGuild%")){
                ((Player)event.getClickedInventory().getHolder()).sendMessage("%AskGuildName%");
                PlayerChatListener.observedPlayerCreate.add((Player) event.getClickedInventory().getHolder());
                event.getView().close();
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("%LeaveGuild%")){
                ((Player)event.getClickedInventory().getHolder()).sendMessage("%LeaveGuildMSG%");
                Main.getGuildManager().leaveGuild(((Player) event.getClickedInventory().getHolder()).getUniqueId().toString(),
                        Main.getGuildManager().getPlayerGuild((Player) event.getClickedInventory().getHolder()).getGuildName());
                event.getView().close();
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("%CloseGuild%")){
                ((Player)event.getClickedInventory().getHolder()).sendMessage("%CloseGuildMSG%");
                Main.getGuildManager().closeGuild(Main.getGuildManager().getPlayerGuild((Player) event.getClickedInventory().getHolder()).getGuildName());
                event.getView().close();
            }
            event.setCancelled(true);
        }
    }


}
