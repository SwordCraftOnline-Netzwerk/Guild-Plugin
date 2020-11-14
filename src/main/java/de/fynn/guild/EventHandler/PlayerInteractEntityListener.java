package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.guis.MyGuild;
import de.fynn.guild.guild.gui.guis.NoGuild;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        if(Main.villagerManager.containsVillager(event.getRightClicked().getUniqueId())){
            if(Main.guildManager.hasGuild(event.getPlayer())){
                event.setCancelled(true);
                event.getPlayer().openInventory(MyGuild.getInventory(event.getPlayer(), Main.guildManager.getPlayerGuild(event.getPlayer())).getInventory());
            }else {
                if(!PlayerChatListener.observedPlayerCreate.contains(event.getPlayer())){
                    event.setCancelled(true);
                    event.getPlayer().openInventory(NoGuild.getInventory(event.getPlayer()).getInventory());
                }else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Main.getMsg(event.getPlayer(),"noDoubleGuildCreate"));
                }
            }
        }
    }

}
