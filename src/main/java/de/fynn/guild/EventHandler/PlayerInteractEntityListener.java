package de.fynn.guild.EventHandler;

import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInteractEntityListener implements Listener {

    GUIBuilder guiBuilder = new GUIBuilder();

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        if(Main.villagerManager.containsVillager(event.getRightClicked().getUniqueId())){
            Inventory inv;
            if(Main.guildManager.hasGuild(event.getPlayer())){
                event.getPlayer().openInventory(
                        (inv = guiBuilder.buildGuildGUI(event.getPlayer(),
                        Main.guildManager.getPlayerGuild(event.getPlayer()).isLeader(event.getPlayer().getUniqueId())))
                        );
                InventoryListener.addInv(event.getPlayer(),inv);
            }else {
                event.getPlayer().openInventory(
                        (inv = guiBuilder.buildCreateGuildGUI(event.getPlayer()))
                );
                InventoryListener.addInv(event.getPlayer(),inv);
            }
        }
    }

}
