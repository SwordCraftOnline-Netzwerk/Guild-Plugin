package de.fynn.guild.EventHandler;

import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        if(Main.villagerManager.containsVillager(event.getRightClicked().getUniqueId())){
            if(Main.guildManager.hasGuild(event.getPlayer())){
                event.getPlayer().openInventory(
                        GUIBuilder.buildGuildGUI(event.getPlayer(),
                        Main.guildManager.getPlayerGuild(event.getPlayer()).isLeader(event.getPlayer().getUniqueId())).getInventory());
            }else {
                event.getPlayer().openInventory(
                        GUIBuilder.buildCreateGuildGUI(event.getPlayer()).getInventory());
            }
        }
    }

}
