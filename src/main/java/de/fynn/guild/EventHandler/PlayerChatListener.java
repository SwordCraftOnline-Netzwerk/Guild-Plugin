package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import de.fynn.guild.guild.permissions.GuildRole;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerChatListener implements Listener {

    public static List<Player> observedPlayerCreate = new ArrayList();
    public static List<Player> observedPlayerRename = new ArrayList<>();
    public static HashMap<Player,String> observedPlayerRole = new HashMap<>();
    public static HashMap<Player,GuildRole> observedPlayerRoleRename = new HashMap<>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if(observedPlayerCreate.contains(event.getPlayer())){
            if(event.getMessage().equals("cancel")){
                observedPlayerCreate.remove(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            Main.guildManager.createGuild(event.getMessage(),event.getPlayer());
            event.getPlayer().sendMessage(Main.getMsg(event.getPlayer(),"guildCreated"));
            observedPlayerCreate.remove(event.getPlayer());
            event.setCancelled(true);
        }else if(observedPlayerRename.contains(event.getPlayer())){
            if(event.getMessage().equals("cancel")){
                observedPlayerRename.remove(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            Main.guildManager.renameGuild(Main.guildManager.getPlayerGuild(event.getPlayer()),event.getMessage());
            observedPlayerRename.remove(event.getPlayer());
            event.setCancelled(true);
        }else if(observedPlayerRole.containsKey(event.getPlayer())){
            Main.guildManager.getPlayerGuild(event.getPlayer()).getRoleManager().createRole(new GuildRole(event.getMessage(),observedPlayerRole.get(event.getPlayer())));
            observedPlayerRole.remove(event.getPlayer());
            event.setCancelled(true);
        }else if(observedPlayerRoleRename.containsKey(event.getPlayer())){
            Main.guildManager.getPlayerGuild(event.getPlayer()).getRoleManager().renameRole(observedPlayerRoleRename.get(event.getPlayer()),event.getMessage());
        }
    }

}
