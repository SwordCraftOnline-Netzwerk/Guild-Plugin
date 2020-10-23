package de.fynn.guild.guild;

import de.fynn.guild.database.DBManager;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GuildManager {

    private HashMap<Player,String> invites = new HashMap<>();
    private HashMap<String, Guild> guilds;

    private DBManager dbManager;

    public GuildManager(DBManager dbManager){
        this.dbManager = dbManager;
        guilds = dbManager.loadGuilds();
    }

    public void createGuild(String guildName, Player player){
        dbManager.createGuild(guildName,player.getUniqueId().toString());
        guilds.put(guildName, dbManager.getGuild(guildName));
    }

    public void addMember(String UUID,String guild){
        guilds.get(guild).addMember(UUID);
        dbManager.addGuildMember(guild,UUID);
    }

    public void removeMember(String UUID,String guild){
        if(guilds.get(guild).removeMember(UUID)){
            dbManager.removeGuildMember(guild,UUID);
        }
        closeGuild(guild);
    }

    public boolean hasGuild(Player player){
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
             guilds.values()) {
            if(guild.containsPlayer(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public boolean isLeader(Player player){
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
                guilds.values()) {
            if(guild.isLeader(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public Guild getPlayerGuild(Player player){
        if(hasGuild(player)){
            if(guilds.isEmpty()){
                return null;
            }
            for (Guild guild:
                    guilds.values()) {
                if(guild.containsPlayer(player.getUniqueId())){
                    return guild;
                }
            }
        }
        return null;
    }

    public void closeGuild(String guild){
        guilds.remove(guild);
        dbManager.deleteGuild(guild);
    }

    public boolean hasInvite(Player player){
        return invites.containsKey(player);
    }

    public String getInvite(Player player){
        return invites.get(player);
    }

    public void invite(Player player, String guild){
        invites.put(player, guild);
    }

}
