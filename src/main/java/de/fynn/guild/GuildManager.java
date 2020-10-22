package de.fynn.guild;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class GuildManager {

    private HashMap<Player,String> invites = new HashMap<>();

    public void createGuild(Player player, String guildName){
        Result result = Main.fileHandler.createGuildFile(guildName,player);
        player.sendMessage(result.getMsg());
        if(result.isSuccess()){
            Main.guilds.put(guildName,Main.fileHandler.getGuild(guildName));
        }
    }

    public void addMember(String UUID,String guild){
        Guild playerGuild = Main.fileHandler.getGuild(guild);
        playerGuild.addMember(UUID);
    }

    public void removeMember(String UUID,String guild){
        Guild playerGuild = Main.fileHandler.getGuild(guild);
        if(playerGuild.containsPlayer(java.util.UUID.fromString(UUID))){
            playerGuild.removeMember(UUID);
        }
    }

    public void leaveGuild(String UUID,String guild){
        Guild playerGuild = Main.fileHandler.getGuild(guild);
        if(playerGuild.containsPlayer(java.util.UUID.fromString(UUID))){
            playerGuild.removeMember(UUID);
        }
    }

    public boolean hasGuild(Player player){
        Collection<Guild> guilds = Main.guilds.values();
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
             guilds) {
            if(guild.containsPlayer(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public boolean isLeader(Player player){
        Collection<Guild> guilds = Main.guilds.values();
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
                guilds) {
            if(guild.isLeader(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public Guild getPlayerGuild(Player player){
        if(hasGuild(player)){
            Collection<Guild> guilds = Main.guilds.values();
            if(guilds.isEmpty()){
                return null;
            }
            for (Guild guild:
                    guilds) {
                if(guild.containsPlayer(player.getUniqueId())){
                    return guild;
                }
            }
        }
        return null;
    }

    public void closeGuild(String guild){
        Main.guilds.remove(guild);
        Main.fileHandler.deleteGuild(guild);
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
