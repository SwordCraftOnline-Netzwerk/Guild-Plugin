package de.fynn.guild.guild;

import de.fynn.guild.Main;
import de.fynn.guild.database.DBManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GuildManager {

    private HashMap<Player, List<Invite>> invites = new HashMap<>();
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

    public void addMember(Player player,String guild){
        guilds.get(guild).addMember(player.getUniqueId().toString());
        dbManager.addGuildMember(guild,player.getUniqueId().toString());
    }

    public void removeMember(String UUID,String guild){
        if(guilds.get(guild).removeMember(UUID)){
            dbManager.removeGuildMember(guild,UUID);
        }else {
            closeGuild(guild);
        }
    }

    public void removeMember(Player player,String guild){
        if(guilds.get(guild).removeMember(player.getUniqueId().toString())){
            dbManager.removeGuildMember(guild,player.getUniqueId().toString());
        }else {
            closeGuild(guild);
        }
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

    public boolean hasGuild(String UUID){
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
                guilds.values()) {
            if(guild.containsPlayer(java.util.UUID.fromString(UUID))){
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

    public boolean isLeader(String UUID){
        if(guilds.isEmpty()){
            return false;
        }
        for (Guild guild:
                guilds.values()) {
            if(guild.isLeader(java.util.UUID.fromString(UUID))){
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

    public Guild getPlayerGuild(String UUID){
        if(hasGuild(UUID)){
            if(guilds.isEmpty()){
                return null;
            }
            for (Guild guild:
                    guilds.values()) {
                if(guild.containsPlayer(java.util.UUID.fromString(UUID))){
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

    public List<Invite> getInvite(Player player){
        return invites.get(player);
    }

    public void invite(Player sender,Player target, String guild){
        Guild guildObj = getGuildFromString(guild);
        if(hasInvite(target)){
            List<Invite> list = invites.get(target);
            for (Invite invite:
                 list) {
                if(invite.getGuild()==guildObj){
                    target.sendMessage(Main.getMsg(target,"filler"));
                    return;
                }
            }
            Invite invite = new Invite(sender.getDisplayName(),guildObj);
        }else {
            Invite invite = new Invite(sender.getDisplayName(),guildObj);
            List<Invite> list = new ArrayList<>();
            list.add(invite);
            invites.put(target,list);
        }
        target.sendMessage(Main.getMsg(target,"filler"));
    }

    public void sendMSGToAllGuildMembers(Player sender,String msg){
        for (Player player:
                getPlayerGuild(sender).getOnlineMembers()) {
            player.sendMessage(Main.getMsg(player,msg));
        }
    }

    public Guild getGuildFromString(String guildName){
        return guilds.get(guildName);
    }

    public void renameGuild(Guild guild, String newName){
        dbManager.renameGuild(guild.getGuildName(),newName);
        guilds.put(newName,guild);
        guilds.remove(guild.getGuildName());
        guild.setGuildName(newName);
    }

}
