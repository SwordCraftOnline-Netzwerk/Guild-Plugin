package de.fynn.guild.guild.permissions;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuildRoleLoader {

    private HashMap<String,GuildRole> roles = new HashMap<>();
    Guild guild;

    public GuildRoleLoader(Guild guild){
        this.guild = guild;
        List<GuildRole> avaiableRoles = Main.getDbManager().getGuildRoles(guild.getGuildName());
        for (GuildRole role:
             avaiableRoles) {
            roles.put(role.getName(),role);
        }
    }

    public GuildRole getRole(String role){
        return roles.get(role);
    }

    public List<GuildRole> getRoles(){
        return new ArrayList<>(roles.values());
    }

    public void addRole(GuildRole role){
        roles.put(role.getName(),role);
    }

    public void removeRole(GuildRole role){
        roles.remove(role.getName());
    }

    public void renameRole(String role, String name){
        GuildRole roleTemp = roles.get(role);
        roles.remove(role);
        roleTemp.rename(name);
        roles.put(name,roleTemp);
    }

    public GuildRole getDefaultRole(){
        return roles.get("default");
    }

}
