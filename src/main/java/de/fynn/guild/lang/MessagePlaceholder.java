package de.fynn.guild.lang;

import de.fynn.guild.Main;
import de.fynn.guild.guild.permissions.GuildRole;
import org.bukkit.entity.Player;

public class MessagePlaceholder {

    public static String getPlaceholder(Player player, String message){
        if(message.contains("%player_name%")){
            return message.replace("%player_name%",player.getDisplayName());
        }else if(message.contains("%guild_name%")){
            return message.replace("%guild_name%", Main.guildManager.hasGuild(player) ? Main.guildManager.getPlayerGuild(player).getGuildName() : "(Gildenlos)");
        }
        return message;
    }

}
