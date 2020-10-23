package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChatListener implements Listener {

    protected static List<Player> observedPlayerCreate = new ArrayList();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if(observedPlayerCreate.contains(event.getPlayer())){
            Main.guildManager.createGuild(event.getMessage(),event.getPlayer());
            observedPlayerCreate.remove(event.getPlayer());
            event.setCancelled(true);
        }
    }

}
