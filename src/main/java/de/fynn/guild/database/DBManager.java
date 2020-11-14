package de.fynn.guild.database;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.permissions.GuildRole;
import de.fynn.guild.guild.permissions.pattern.DefaultRole;
import de.fynn.guild.guild.permissions.pattern.Owner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DBManager {

    private DBConnector dbConnector;
    private String dbName;

    public DBManager(String[] dbinfo){
        dbConnector = new DBConnector(dbinfo);
        dbName = dbinfo[3];
        init();
    }

    private void init(){
        dbConnector.executeSQL("CREATE SCHEMA IF NOT EXISTS "+dbName+";",false);
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".villagers (UUID VARCHAR(150) NOT NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC));",false);
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".guilds (guild VARCHAR(150) NOT NULL,leader VARCHAR(150) NULL,PRIMARY KEY (guild));",false);
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".lang (UUID VARCHAR(150) NOT NULL,lang VARCHAR(150) NULL,PRIMARY KEY (UUID));",false);
    }

    public HashMap<String, Guild> loadGuilds(){
        HashMap<String,Guild> allGildes = new HashMap<>();
        ResultSet result = dbConnector.getData("SELECT guild FROM "+dbName+".guilds");
        List<String> guilds = new ArrayList<>();
        try{
            while(result.next()){
                guilds.add(result.getString(1));
            }
            result.close();
            for (String s:
                 guilds) {
                allGildes.put(s,getGuild(s));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return allGildes;
    }

    public List<UUID> loadVillagers(){
        ResultSet result = dbConnector.getData("SELECT UUID FROM "+dbName+".villagers;");
        List<UUID> uuids = new ArrayList<>();
        try {
            while(result.next()){
                uuids.add(UUID.fromString(result.getString(1)));
            }
            result.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return uuids;
    }

    public void createGuild(String guild, String owner){
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+"."+guild+" (UUID VARCHAR(150) NOT NULL,displayname VARCHAR(150) NULL,role VARCHAR(150) NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC));",false);
        dbConnector.executeSQL("INSERT INTO "+dbName+".guilds(guild,leader) VALUES ('"+guild+"','"+owner+"');",false);
        dbConnector.executeSQL("INSERT INTO "+dbName+"."+guild+"(UUID,displayname,role) VALUES ('"+owner+"','"+ Bukkit.getPlayer(UUID.fromString(owner)).getName()+"','owner');",false);
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+"."+guild+"Roles (role VARCHAR(150) NOT NULL,value VARCHAR(150) NOT NULL, PRIMARY KEY (role));",false);
        addGuildRoleToGuild(guild,new DefaultRole());
        addGuildRoleToGuild(guild,new Owner());
    }

    public Guild getGuild(String guild){
        try {
            ResultSet result = dbConnector.getData("SELECT * FROM "+dbName+"."+guild+";");
            List<UUID> gildMembers = new ArrayList<>();
            while(result.next()){
                gildMembers.add(UUID.fromString(result.getString(1)));
            }
            result.close();
            result = dbConnector.getData("SELECT leader FROM "+dbName+".guilds WHERE guild = '"+guild+"';");
            String leader ="";
            while(result.next()){
                leader = result.getString(1);
            }
            result.close();
            return new Guild(guild,UUID.fromString(leader),gildMembers);
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public void deleteGuild(String guild){
        dbConnector.executeSQL("DROP TABLE "+dbName+"."+guild+";",true);
        dbConnector.executeSQL("DELETE FROM "+dbName+".guilds WHERE guild = '"+guild+"';",true);
        dbConnector.executeSQL("DROP TABLE "+dbName+"."+guild+"Roles;",true);
    }

    public void addGuildMember(String guild, String uuid){
        dbConnector.executeSQL("INSERT INTO "+dbName+"."+guild+"(UUID,displayname,role) VALUES ('"+uuid+"','"+Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName()+"','default');",false);
    }

    public void removeGuildMember(String guild, String uuid){
        dbConnector.executeSQL("DELETE FROM "+dbName+"."+guild+" WHERE UUID = '"+uuid+"';",true);
    }

    public void addVillager(String uuid){
        dbConnector.executeSQL("INSERT INTO "+dbName+".villagers(UUID) VALUES ('"+uuid+"');",false);
    }

    public void removeVillager(String uuid){
        dbConnector.executeSQL("DELETE FROM "+dbName+".villagers WHERE UUID = '"+uuid+"';",true);
    }

    public boolean hasLanguage(Player player){
        ResultSet result = dbConnector.getData("SELECT EXISTS(SELECT * FROM "+dbName+".lang WHERE UUID = '"+player.getUniqueId().toString()+"');");
        try {
            if(!result.next()){
                return false;
            }
            return result.getInt(1)==1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public String getLanguage(Player player){
        ResultSet result = dbConnector.getData("SELECT lang FROM "+dbName+".lang WHERE UUID = '"+player.getUniqueId().toString()+"';");
        try {
            if(!result.next()){
                return "default";
            }
            return result.getString(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void setLanguage(Player player, String language){
        if(hasLanguage(player)){
            dbConnector.executeSQL("UPDATE "+dbName+".lang SET lang = '"+language+"' WHERE UUID = '"+player.getUniqueId().toString()+"';",true);
        }else {
            dbConnector.executeSQL("INSERT INTO "+dbName+".lang(UUID,lang) VALUES ('"+player.getUniqueId().toString()+"','"+language+"');",true);
        }
    }

    public String getGuildRole(Player player, Guild guild){
        ResultSet result = dbConnector.getData("SELECT role FROM "+dbName+"."+guild.getGuildName()+" WHERE UUID = '"+player.getUniqueId().toString()+"';");
        try {
            if(!result.next()){
                return "default";
            }
            return result.getString(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void setGuildRole(Player player, String role){
        dbConnector.executeSQL("UPDATE "+dbName+"."+Main.guildManager.getPlayerGuild(player).getGuildName()+" SET role = "+role+" WHERE UUID = '"+player.getUniqueId().toString()+"';",true);
    }

    public void addGuildRoleToGuild(String guild, GuildRole role){
        dbConnector.executeSQL("INSERT INTO "+dbName+"."+guild+"Roles(role,value) VALUES ('"+role.getName()+"','"+role.toString()+"');",false);
    }

    public void updateRole(String guild, GuildRole role){
        deleteRole(guild,role.getName());
        addGuildRoleToGuild(guild, role);
        //dbConnector.executeSQL("UPDATE "+dbName+"."+guild+"Roles SET value = "+role.toString()+" WHERE role = '"+role.getName()+"';");
    }

    public List<GuildRole> getGuildRoles(String guild){
        ResultSet result = dbConnector.getData("SELECT * FROM "+dbName+"."+guild+"Roles");
        List<GuildRole> roles = new ArrayList<>();
        try {
            while (result.next()){
                String name = result.getString(1);
                String perm = result.getString(2);
                roles.add(new GuildRole(name,perm));
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return roles;
    }

    public String getDisplayname(String UUID, Guild guild){
        ResultSet result = dbConnector.getData("SELECT displayname FROM "+dbName+"."+guild.getGuildName()+" WHERE UUID = '"+UUID+"';");
        try {
            while (result.next()){
                return result.getString(1);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return "";
    }

    public void renameGuild(String guildName, String newName){
        dbConnector.executeSQL("ALTER TABLE "+dbName+"."+guildName+" RENAME TO "+dbName+"."+newName+";",false);
        dbConnector.executeSQL("ALTER TABLE "+dbName+"."+guildName+"Roles RENAME TO "+dbName+"."+newName+"Roles;",false);
        dbConnector.executeSQL("UPDATE "+dbName+".guilds SET guild = '"+newName+"' WHERE guild = '"+guildName+"';",false);
    }

    public void renameRole(String guild, String oldName, String newName){
        dbConnector.executeSQL("UPDATE "+dbName+"."+guild+"Roles SET role = '"+newName+"' WHERE role = '"+oldName+"';",true);
    }

    public void deleteRole(String guild,String role){
        dbConnector.executeSQL("DELETE FROM "+dbName+"."+guild+"Roles WHERE role = '"+role+"';",true);
    }

}