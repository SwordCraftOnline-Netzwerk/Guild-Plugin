package de.fynn.guild.EventHandler;

import de.fynn.guild.guild.GUIBuilder;
import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {

    GUIBuilder guiBuilder = new GUIBuilder();

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        if(Main.villagerManager.containsVillager(event.getRightClicked().getUniqueId())){
            if(Main.guildManager.hasGuild(event.getPlayer())){
                event.getPlayer().openInventory(guiBuilder.buildGuildGUI(event.getPlayer(),
                        Main.guildManager.getPlayerGuild(event.getPlayer()).isLeader(event.getPlayer().getUniqueId())));
            }else {
                event.getPlayer().openInventory(guiBuilder.buildCreateGuildGUI(event.getPlayer()));
            }
        }
    }

}
