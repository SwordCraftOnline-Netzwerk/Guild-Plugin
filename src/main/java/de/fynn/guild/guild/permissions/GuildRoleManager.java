package de.fynn.guild.guild.permissions;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GuildRoleManager {

    private HashMap<Player, GuildRole> permissions = new HashMap<>();
    private GuildRoleLoader loader;
    private Guild guild;

    public GuildRoleManager(Guild guild){
        loader = new GuildRoleLoader(guild);
        for (Player player:
             guild.getOnlineMembers()) {
            permissions.put(player, loader.getRole(Main.getDbManager().getGuildRole(player,guild)));
        }
        this.guild = guild;
    }

    public void addPlayer(Player player, GuildRole role){
        permissions.put(player, role);
    }

    public void removePlayer(Player player){
        permissions.remove(player);
    }

    public boolean hasPermission(Player player,GuildPermissions permission){
        return permissions.get(player).containsPermission(permission);
    }

    public boolean hasHigherPriority(Player p1, Player p2){
        return  isHigher(permissions.get(p1),permissions.get(p2));
    }

    public boolean hasHigherOrEqualsPriority(Player p1, Player p2){
        return isHigher(permissions.get(p1),permissions.get(p2)) || isEquals(permissions.get(p1), permissions.get(p2));
    }

    public boolean isHigher(GuildRole role1, GuildRole role2){
        return Integer.parseInt(role1.getPriority()) > Integer.parseInt(role2.getPriority());
    }

    public boolean isEquals(GuildRole role1, GuildRole role2){
        return Integer.parseInt(role1.getPriority()) == Integer.parseInt(role2.getPriority());
    }

    public GuildRoleLoader getLoader(){
        return loader;
    }

    public GuildRole getRole(Player player){
        return permissions.get(player);
    }

    public void setRole(Player player, GuildRole role){
        permissions.put(player, role);
    }

    public void createRole(GuildRole role){
        getLoader().addRole(role);
        Main.getDbManager().addGuildRoleToGuild(guild.getGuildName(),role);
    }

    public void editRole(GuildRole roleOld, GuildRole roleNew){
        loader.removeRole(roleOld);
        loader.addRole(roleNew);
        Main.getDbManager().updateRole(guild.getGuildName(),roleNew);
    }

    public void renameRole(GuildRole role, String name){
        loader.getRole(role.getName()).rename(name);
        Main.getDbManager().renameRole(guild.getGuildName(),role.getName(),name);
    }

    public void deleteRole(GuildRole role){
        loader.removeRole(role);
        Main.getDbManager().deleteRole(guild.getGuildName(),role.getName());
    }

}
