package de.fynn.guild.api;

import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GuildPlaceholder {

    public String setPlaceholder(Player player, String text){
        if(player==null){
            return null;
        }else if(text.contains("%guild_name%")){
            text = text.replace("%guild_name%",Main.getGuildManager().hasGuild(player) ? Main.getGuildManager().getPlayerGuild(player).getGuildName() : "Gildenlos");
        }else if(text.contains("%guild_members_amount%")){
            text = text.replace("%guild_members_amount%",Main.getGuildManager().hasGuild(player) ? Main.getGuildManager().getPlayerGuild(player).getMembers().size()+"1" : "0");
        }else  if(text.contains("%guild_leader%")){
            text = text.replace("%guild_leader%",Main.getGuildManager().hasGuild(player) ? Bukkit.getPlayer(Main.getGuildManager().getPlayerGuild(player).getLeader()).getDisplayName() : "");
        }
        return text;
    }

}
