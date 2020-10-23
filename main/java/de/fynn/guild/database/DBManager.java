package de.fynn.guild.database;

import de.fynn.guild.guild.Guild;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DBManager {

    private DBConnector dbConnector;

    public DBManager(String[] dbinfo){
        dbConnector = new DBConnector(dbinfo);
        init();
    }

    private void init(){
        dbConnector.executeSQL("CREATE SCHEMA IF NOT EXISTS guild");
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS guild.villagers (UUID VARCHAR(200) NOT NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC) VISIBLE);");
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS guild.guilds (guild VARCHAR(200) NOT NULL,leader VARCHAR(200) NULL,PRIMARY KEY (guild));");
    }

    public HashMap<String, Guild> loadGuilds(){
        HashMap<String,Guild> allGildes = new HashMap<>();
        ResultSet result = dbConnector.getData("SELECT guild FROM guild.guilds");
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
        ResultSet result = dbConnector.getData("SELECT UUID FROM guild.villagers;");
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
        dbConnector.executeSQL("CREATE TABLE IF NOT EXISTS guild."+guild+" (UUID VARCHAR(200) NOT NULL,PRIMARY KEY (UUID),UNIQUE INDEX UUID_UNIQUE (UUID ASC) VISIBLE);");
        dbConnector.executeSQL("INSERT INTO guild.guilds(guild,leader) VALUES ('"+guild+"','"+owner+"');");
    }

    public Guild getGuild(String guild){
        try {
            ResultSet result = dbConnector.getData("SELECT * FROM guild."+guild+";");
            List<UUID> gildMembers = new ArrayList<>();
            while(result.next()){
                gildMembers.add(UUID.fromString(result.getString(1)));
            }
            result.close();
            result = dbConnector.getData("SELECT leader FROM guild.guilds WHERE guild = '"+guild+"';");
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
        dbConnector.executeSQL("DROP TABLE guild."+guild+";");
        dbConnector.executeSQL("DELETE FROM guild.guilds WHERE guild = '"+guild+"';");
    }

    public void addGuildMember(String guild, String uuid){
        dbConnector.executeSQL("INSERT INTO guild."+guild+"(UUID) VALUES ("+uuid+");");
    }

    public void removeGuildMember(String guild, String uuid){
        dbConnector.executeSQL("DELETE FROM guild."+guild+" WHERE UUID = '"+uuid+"';");
    }

    public void addVillager(String uuid){
        dbConnector.executeSQL("INSERT INTO guild.villagers(UUID) VALUES ('"+uuid+"');");
    }

    public void removeVillager(String uuid){
        dbConnector.executeSQL("DELETE FROM guild.villagers WHERE UUID = '"+uuid+"';");
    }

}