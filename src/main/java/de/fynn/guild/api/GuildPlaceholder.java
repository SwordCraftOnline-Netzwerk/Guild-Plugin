package de.fynn.guild.api;

import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GuildPlaceholder {

    public String setPlaceholder(Player player, String placeholder){
        if(player==null){
            return null;
        }
        switch (placeholder){

            case "%guild_name":
                return Main.getGuildManager().hasGuild(player) ? Main.getGuildManager().getPlayerGuild(player).getGuildName() : "Gildenlos";

            case "%guild_members_amount":
                return Main.getGuildManager().hasGuild(player) ? Main.getGuildManager().getPlayerGuild(player).getMembers().size()+"1" : "0";

            case "%guild_leader":
                return Main.getGuildManager().hasGuild(player) ? Bukkit.getPlayer(Main.getGuildManager().getPlayerGuild(player).getLeader()).getDisplayName() : "";

            default:
                return null;
        }
    }

}
