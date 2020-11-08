package de.fynn.guild.guild;

import de.fynn.guild.Main;
import de.fynn.guild.guild.permissions.GuildRoleManager;
import de.fynn.guild.guild.permissions.pattern.DefaultRole;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Guild {

    private String guildName;
    private UUID leader;
    private List<UUID> members = new ArrayList<>();
    private List<Player> onlineMembers = new ArrayList<>();

    private GuildRoleManager roleManager;

    public Guild(String name, UUID leader, List<UUID> members){
        guildName = name;
        this.leader = leader;
        this.members = members;
        roleManager = new GuildRoleManager(this);
        Player player;
        for (UUID uuid:
                members) {
            player = Bukkit.getPlayer(uuid);
            if(player!=null){
                addOnlineMember(player);
            }
        }
    }

    protected boolean containsPlayer(UUID uuid){
        return members.contains(uuid);
    }

    public boolean isLeader(UUID uuid){
        return uuid.equals(leader);
    }

    protected void addMember(String UUID){
        members.add(java.util.UUID.fromString(UUID));
        addOnlineMember(Bukkit.getPlayer(java.util.UUID.fromString(UUID)));
        roleManager.addPlayer(Bukkit.getPlayer(java.util.UUID.fromString(UUID)),new DefaultRole());
    }

    protected boolean removeMember(String UUID){
        removeOnlineMember(Bukkit.getPlayer(java.util.UUID.fromString(UUID)));
        roleManager.removePlayer(Bukkit.getPlayer(java.util.UUID.fromString(UUID)));
        if(isLeader(java.util.UUID.fromString(UUID))){
            members.remove(0);
            if(members.size()==0){
                return false;
            }
            leader = members.get(0);
        }
        members.remove(java.util.UUID.fromString(UUID));
        return true;
    }

    public String getGuildName() {
        return guildName;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public UUID getLeader() {
        return leader;
    }

    public GuildRoleManager getRoleManager(){
        return roleManager;
    }

    public void addOnlineMember(Player player){
        onlineMembers.add(player);
        roleManager.addPlayer(player,roleManager.getLoader().getRole(Main.getDbManager().getGuildRole(player,this)));
    }

    public void removeOnlineMember(Player player){
        onlineMembers.remove(player);
        roleManager.removePlayer(player);
    }

    public boolean isOnline(Player player){
        return onlineMembers.contains(player);
    }

    public List<Player> getOnlineMembers(){
        return onlineMembers;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }
}
