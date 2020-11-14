package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickListener implements Listener {

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event){
        if(!Main.guildManager.hasGuild(event.getPlayer())){
            Main.guildlessPlayer.remove(event.getPlayer());
        }else {
            Main.guildManager.getPlayerGuild(event.getPlayer()).removeOnlineMember(event.getPlayer());
        }
    }

}
