package de.fynn.guild.database;

import de.fynn.guild.guild.Guild;
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
        dbConnector.executeSQL("CREATE SCHEMA IF NOT EXISTS "+dbName+";");
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".villagers (UUID VARCHAR(150) NOT NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC));");
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".guilds (guild VARCHAR(150) NOT NULL,leader VARCHAR(150) NULL,PRIMARY KEY (guild));");
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+".lang (UUID VARCHAR(150) NOT NULL,lang VARCHAR(150) NULL,PRIMARY KEY (UUID));");
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
            System.out.println("LoadGuilds");
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
            System.out.println("Load Villagers");
            exception.printStackTrace();
        }
        return uuids;
    }

    public void createGuild(String guild, String owner){
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS "+dbName+"."+guild+" (UUID VARCHAR(150) NOT NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC));");
        dbConnector.executeSQL("INSERT INTO "+dbName+".guilds(guild,leader) VALUES ('"+guild+"','"+owner+"');");
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
            System.out.println("Get Guild");
            exception.printStackTrace();
        }
        return null;
    }

    public void deleteGuild(String guild){
        dbConnector.executeSQL("DROP TABLE "+dbName+"."+guild+";");
        dbConnector.executeSQL("DELETE FROM "+dbName+".guilds WHERE guild = '"+guild+"';");
    }

    public void addGuildMember(String guild, String uuid){
        dbConnector.executeSQL("INSERT INTO "+dbName+"."+guild+"(UUID) VALUES ("+uuid+");");
    }

    public void removeGuildMember(String guild, String uuid){
        dbConnector.executeSQL("DELETE FROM "+dbName+"."+guild+" WHERE UUID = '"+uuid+"';");
    }

    public void addVillager(String uuid){
        dbConnector.executeSQL("INSERT INTO "+dbName+".villagers(UUID) VALUES ('"+uuid+"');");
    }

    public void removeVillager(String uuid){
        dbConnector.executeSQL("DELETE FROM "+dbName+".villagers WHERE UUID = '"+uuid+"';");
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
            dbConnector.executeSQL("UPDATE "+dbName+".lang SET lang = '"+language+"' WHERE UUID = '"+player.getUniqueId().toString()+"';");
        }else {
            dbConnector.executeSQL("INSERT INTO "+dbName+".lang(UUID,lang) VALUES ('"+player.getUniqueId().toString()+"','"+language+"');");
        }
    }

}