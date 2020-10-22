package de.fynn.guild;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FileHandler {

    private File file;
    private FileConfiguration villagerUUIDs;

    public FileHandler(){
        init();
    }

    private void init(){
        try {
            Files.createDirectories(new File(Main.getPlugin().getDataFolder()+"/Guilds").toPath());
            file = new File(Main.getPlugin().getDataFolder()+"/villager.yml");
            file.createNewFile();
            villagerUUIDs = YamlConfiguration.loadConfiguration(file);
            villagerUUIDs.addDefault("Amount",0);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected Result createGuildFile(String guildName, Player owner){
        if(Main.guilds.containsKey(guildName)){
            return new Result(false,"%GuildExists%");
        }
        try {
            Files.createDirectories(new File(Main.getPlugin().getDataFolder()+"/Guilds").toPath());
            File file = new File(Main.getPlugin().getDataFolder()+"/Guilds/"+guildName+".yml");
            file.createNewFile();
            FileConfiguration guildYAML = YamlConfiguration.loadConfiguration(file);
            guildYAML.set("Leader",owner.getUniqueId().toString());
            guildYAML.save(file);
            Main.guilds.put(guildName,new Guild(file));
        }catch (IOException e){
            e.printStackTrace();
            return new Result(false,"%FileError%");
        }
        return new Result(true,"%GuildCreateSuccess%");
    }

    protected Guild getGuild(String guildName){
        File file = new File(Main.getPlugin().getDataFolder()+"/Guilds/"+guildName+".yml");
        if(file.exists()){
            return new Guild(file);
        }
        return null;
    }

    private Guild getGuild(File file){
        return new Guild(file);
    }

    protected HashMap<String, Guild> loadAllGuilds(){
        HashMap<String,Guild> guilds = new HashMap<>();
        File file = new File(Main.getPlugin().getDataFolder()+"/Guilds");
        File[] guildFiles = file.listFiles();
        for (File f:
             guildFiles) {
            guilds.put(f.getName().substring(0,f.getName().length()-4),getGuild(f));
        }
        return guilds;
    }

    protected List<UUID> getVillager(){
        List<UUID> uuids = new ArrayList<>();
        for (int i = 0; i < villagerUUIDs.getInt("Amount"); i++) {
            uuids.add(UUID.fromString(villagerUUIDs.getString("UUID."+(i+1))));
        }
        return uuids;
    }

    public void addVillager(UUID uuid){
        villagerUUIDs.set("Amount",villagerUUIDs.getInt("Amount")+1);
        villagerUUIDs.set("UUID."+villagerUUIDs.getInt("Amount"),uuid.toString());
        try {
            villagerUUIDs.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeVillager(UUID uuid){
        List<String> villagers = new ArrayList<>();
        for (int i = 0; i < villagerUUIDs.getInt("Amount"); i++) {
            villagers.add(villagerUUIDs.getString("UUID."+(i+1)));
        }
        villagers.remove(uuid.toString());
        villagerUUIDs.set("Amount",villagerUUIDs.getInt("Amount")-1);
        villagerUUIDs.set("UUID",null);
        for (int i = 0; i < villagerUUIDs.getInt("Amount"); i++) {
            villagerUUIDs.set("UUID."+(i+1),villagers.get(0));
            villagers.remove(0);
        }
        try {
            villagerUUIDs.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void deleteGuild(String guildName) {
        File file = new File(Main.getPlugin().getDataFolder()+"/Guilds/"+guildName+".yml");
        file.delete();
    }
}
