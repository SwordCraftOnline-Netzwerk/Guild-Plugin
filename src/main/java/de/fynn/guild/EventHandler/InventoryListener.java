package de.fynn.guild.EventHandler;

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
        if(event.getView().getTitle().equals("Gilde verwalten")||event.getView().getTitle().equals("Gilde gründen")){
            Player player = ((Player)event.getClickedInventory().getHolder());
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("neue Gilde gründen")){
                player.sendMessage("Um die erstellung der Gilde abzuschließen, schreibe den gewünschten Namen der Gilde in den Chat");
                PlayerChatListener.observedPlayerCreate.add(player);
                event.getView().close();
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("Gilde verlassen")){
                player.sendMessage("Du hast die Gilde "+Main.guildManager.getPlayerGuild(player).getGuildName()+" verlassen!");
                Main.guildManager.removeMember(player.getUniqueId().toString(),
                        Main.guildManager.getPlayerGuild(player).getGuildName());
                event.getView().close();
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("Gilde auflösen")){
                player.sendMessage("Die Gilde "+Main.guildManager.getPlayerGuild(player).getGuildName()+" wurde erfolgreich aufgelöst!");
                Main.guildManager.closeGuild(Main.guildManager.getPlayerGuild(player).getGuildName());
                event.getView().close();
            }
            event.setCancelled(true);
        }
    }


}
